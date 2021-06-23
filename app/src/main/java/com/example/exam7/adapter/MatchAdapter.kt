package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.exam7.adapter.base.BaseAdapter
import com.example.exam7.adapter.base.BaseViewHolder
import com.example.exam7.databinding.MatchInfoLayoutBinding
import com.example.exam7.databinding.ParentRecyclerItemLayoutBinding
import com.example.exam7.extension.load
import com.example.exam7.extension.toDateFormat
import com.example.exam7.model.Match
import com.example.exam7.model.Summary
import java.lang.Exception

typealias GetMatchInfo = () -> Match

class MatchAdapter :
    BaseAdapter<Summary>() {
    companion object {
        private const val MATCH_INFO_TYPE = 1
        private const val MATCH_ACTION_TYPE = 2
    }

    lateinit var getMatchInfo: GetMatchInfo

    override fun getItemCount() = items.size

    fun addItems(items: List<Summary>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

//    override fun onBindViewHolder(holder: BaseViewHolder<*,ViewBinding>, position: Int) {
//        when(holder){
//            is MatchActionViewHolder -> holder.bind(items[position - 1])
//            is MatchInfoViewHolder -> holder.bind(getMatchInfo())
//        }
//    }


    override fun getItemViewType(position: Int) =
        MATCH_ACTION_TYPE


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Summary,ViewBinding> {
        return when (viewType) {
            MATCH_ACTION_TYPE ->
                MatchActionViewHolder(
                    ParentRecyclerItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw Exception("invalid type")
        }
    }

    inner class MatchActionViewHolder(private val binding: ParentRecyclerItemLayoutBinding) :
        BaseViewHolder<Summary,ParentRecyclerItemLayoutBinding>(binding) {
        override fun bind(data: Summary) {
            binding.rvTeam1.adapter = TeamAdapter().apply {
                actionTime = { data.actionTime!! }
                addItems(data.team1Action)
            }
            binding.rvTeam1.layoutManager = LinearLayoutManager(binding.root.context)


            binding.rvTeam2.adapter = TeamAdapter().apply {
                actionTime = { data.actionTime!! }
                addItems(data.team2Action)
            }
            binding.rvTeam2.layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    inner class MatchInfoViewHolder(private val binding: MatchInfoLayoutBinding) :
        BaseViewHolder<Match,MatchInfoLayoutBinding>(binding) {
        override fun bind(data: Match) {
            with(binding) {

                tvDate.text = data.matchDate?.toDateFormat("d MMMM yyyy")

                tvStadium.text = data.stadiumAdress

                val time = "${data.matchTime}'"
                tvTime.text = time

                ivTeam1.load(data.team1?.teamImage!!)
                ivTeam2.load(data.team2?.teamImage!!)

                tvTeam1.text = data.team1.teamName
                tvTeam2.text = data.team2.teamName

                val score = "${data.team1.score} : ${data.team2.score}"
                tvScore.text = score

                tvTime.text = data.matchTime.toString()

                tvBallPossession1.text = data.team1.ballPosition.toString()
                tvBallPossession2.text = data.team2.ballPosition.toString()

                progress.progress = data.team1.ballPosition!!

                val halfScore = "${data.team1.score} : ${data.team2.score}"
                tvHalfScore.text = halfScore
            }
        }
    }
}