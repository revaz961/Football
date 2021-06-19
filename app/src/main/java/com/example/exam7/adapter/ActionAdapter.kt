package com.example.exam7.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.exam7.R
import com.example.exam7.databinding.Team1ItemLayoutBinding
import com.example.exam7.databinding.Team2ItemLayoutBinding
import com.example.exam7.extension.load
import com.example.exam7.extension.textColor
import com.example.exam7.extension.visible
import com.example.exam7.fragment.Inflate
import com.example.exam7.model.GoalType
import com.example.exam7.model.MatchActionType
import com.example.exam7.model.TeamAction

typealias GetActionTime = () -> String


class ActionAdapter<VB : ViewBinding>(val inflate: Inflate<VB>) :
    RecyclerView.Adapter<ActionAdapter<VB>.ViewHolder>() {

    lateinit var actionTime: GetActionTime

    private val items = mutableListOf<TeamAction>()

    fun setItems(list: List<TeamAction>?) {
        if (list != null) {
            items.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: TeamAction

        fun bind() {
            model = items[adapterPosition]
            when (binding) {
                is Team1ItemLayoutBinding -> initTeam1(binding)
                is Team2ItemLayoutBinding -> initTeam2(binding)
            }
        }

        private fun initTeam1(binding: Team1ItemLayoutBinding) {
            binding.ivPlayer1Image.load(
                model.action?.player1?.playerImage!!,
                R.drawable.dinamo,
                R.drawable.dinamo
            )

            binding.tvPlayer1Name.text = model.action?.player1?.playerName!!

            var actionType = "${actionTime()}' "
            when (model.actionType) {

                MatchActionType.GOAL.type -> {

                    when (model.action?.goalType) {

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

                    binding.ivActionImage1.setImageResource(R.drawable.group_56)
                    binding.ivActionImage2.visible(true)

                    binding.tvPlayer2Name.text = model.action?.player2?.playerName!!

                    binding.ivPlayer2Image.load(
                        model.action?.player2?.playerImage,
                        R.drawable.team2,
                        R.drawable.team2
                    )
                }
            }
            binding.tvActionType.text = actionType
        }

        private fun initTeam2(binding: Team2ItemLayoutBinding) {

            binding.ivPlayer1Image.load(
                model.action?.player1?.playerImage,
                R.drawable.team2,
                R.drawable.team2
            )

            binding.tvPlayer1Name.text = model.action?.player1?.playerName!!

            var actionType = "${actionTime()}' "

            when (model.actionType) {

                MatchActionType.GOAL.type -> {

                    when (model.action?.goalType) {

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

                    binding.ivActionImage1.setImageResource(R.drawable.group_56)
                    binding.ivActionImage2.visible(true)

                    binding.tvPlayer2Name.text = model.action?.player2?.playerName!!

                    binding.ivPlayer2Image.load(
                        model.action?.player2?.playerImage,
                        R.drawable.dinamo,
                        R.drawable.dinamo
                    )
                }
            }
            binding.tvActionType.text = actionType
        }
    }
}