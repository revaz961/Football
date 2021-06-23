package com.example.exam7.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam7.R
import com.example.exam7.adapter.base.BaseAdapter
import com.example.exam7.adapter.base.IBaseViewHolder
import com.example.exam7.databinding.Team1ItemLayoutBinding
import com.example.exam7.databinding.Team2ItemLayoutBinding
import com.example.exam7.extension.load
import com.example.exam7.extension.textColor
import com.example.exam7.extension.visible
import com.example.exam7.constants.GoalType
import com.example.exam7.constants.MatchActionType
import com.example.exam7.constants.MatchTeamType
import com.example.exam7.model.TeamAction
import java.lang.Exception

typealias GetActionTime = () -> String

class TeamAdapter : BaseAdapter<TeamAction>() {
    companion object {
        private const val TEAM1_TYPE = 1
        private const val TEAM2_TYPE = 2
    }

    lateinit var actionTime: GetActionTime

    fun addItems(items: List<TeamAction>?) {
        if (!items.isNullOrEmpty()) {
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun setViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when (type) {
            TEAM1_TYPE -> Team1ViewHolder(
                Team1ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TEAM2_TYPE -> Team2ViewHolder(
                Team2ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw Exception("invalid type")
        }
    }

    override fun getItemViewType(position: Int) = when (items[position].teamType) {
        MatchTeamType.TEAM1.team -> TEAM1_TYPE
        MatchTeamType.TEAM2.team -> TEAM2_TYPE
        else -> throw Exception("invalid type")
    }

    inner class Team1ViewHolder(private val binding: Team1ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), IBaseViewHolder<TeamAction> {
        override fun bind(data: TeamAction) {
            binding.ivPlayer1Image.load(
                data.action?.player1?.playerImage!!
            )

            binding.tvPlayer1Name.text = data.action.player1.playerName!!

            var actionType = "${actionTime()}' "
            when (data.actionType) {

                MatchActionType.GOAL.type -> {

                    when (data.action.goalType) {

                        GoalType.GOAL.type -> {
                            actionType += "Goals by"
                            binding.ivActionImage1.setImageResource(R.drawable.path_44)
                        }

                        GoalType.OWN_GOAL.type -> {
                            actionType += "Own Goal"
                            binding.tvActionType.textColor(R.color.red)
                            binding.ivActionImage1.setImageResource(R.drawable.path_46)
                        }
                    }
                }

                MatchActionType.YELLOW_CARD.type -> {
                    actionType += "Tripping"
                    binding.ivActionImage1.setImageResource(R.drawable.path_81)
                }

                MatchActionType.RED_CARD.type -> {
                    actionType += "Tripping"
                    binding.ivActionImage1.setImageResource(R.drawable.path_81)
                    binding.ivActionImage1.setColorFilter(Color.rgb(255, 0, 0))
                }

                MatchActionType.SUBSTITUTION.type -> {
                    actionType += "Substitution"

                    with(binding){
                        ivActionImage1.setImageResource(R.drawable.group_56)
                        ivActionImage2.visible(true)

                        tvPlayer2Name.text = data.action.player2?.playerName!!

                        ivPlayer2Image.visible(true)
                        ivPlayer2Image.load(
                            data.action.player2.playerImage
                        )
                    }

                }
            }
            binding.tvActionType.text = actionType
        }
    }

    inner class Team2ViewHolder(private val binding: Team2ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), IBaseViewHolder<TeamAction> {
        override fun bind(data: TeamAction) {

            binding.ivPlayer1Image.load(
                data.action?.player1?.playerImage
            )

            binding.tvPlayer1Name.text = data.action?.player1?.playerName!!

            var actionType = "${actionTime()}' "

            when (data.actionType) {

                MatchActionType.GOAL.type -> {

                    when (data.action.goalType) {

                        GoalType.GOAL.type -> {
                            actionType += "Goals by"
                            binding.ivActionImage1.setImageResource(R.drawable.path_44)
                        }

                        GoalType.OWN_GOAL.type -> {
                            actionType += "Own Goal"
                            binding.ivActionImage1.setImageResource(R.drawable.path_46)
                            binding.tvActionType.textColor(R.color.red)
                        }
                    }
                }

                MatchActionType.YELLOW_CARD.type -> {
                    actionType += "Tripping"
                    binding.ivActionImage1.setImageResource(R.drawable.path_81)
                }

                MatchActionType.RED_CARD.type -> {
                    actionType += "Tripping"
                    binding.ivActionImage1.setImageResource(R.drawable.path_81)
                    binding.ivActionImage1.setColorFilter(Color.rgb(255, 0, 0))
                }

                MatchActionType.SUBSTITUTION.type -> {
                    actionType += "Substitution"

                    with(binding){
                        ivActionImage1.setImageResource(R.drawable.group_56)
                        ivActionImage2.visible(true)

                        tvPlayer2Name.text = data.action.player2?.playerName!!

                        ivPlayer2Image.visible(true)
                        ivPlayer2Image.load(
                            data.action.player2.playerImage
                        )
                    }
                }
            }
            binding.tvActionType.text = actionType
        }
    }
}