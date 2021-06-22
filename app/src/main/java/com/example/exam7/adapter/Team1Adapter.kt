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

class Team1Adapter :
    BaseAdapter<TeamAction, Team1ItemLayoutBinding>(Team1ItemLayoutBinding::inflate) {

    lateinit var actionTime: GetActionTime

    override fun bind(binding: Team1ItemLayoutBinding, item: TeamAction) {

        binding.ivPlayer1Image.load(
            item.action?.player1?.playerImage!!
        )

        binding.tvPlayer1Name.text = item.action.player1.playerName!!

        var actionType = "${actionTime()}' "
        when (item.actionType) {

            MatchActionType.GOAL.type -> {

                when (item.action.goalType) {

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

                binding.tvPlayer2Name.text = item.action.player2?.playerName!!

                binding.ivPlayer2Image.load(
                    item.action.player2.playerImage
                )
            }
        }
        binding.tvActionType.text = actionType
    }
}
