package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam7.adapter.base.BaseAdapter
import com.example.exam7.adapter.base.Binder
import com.example.exam7.databinding.ParentRecyclerItemLayoutBinding
import com.example.exam7.model.Summary
import java.lang.Exception

class MatchAdapter :
    BaseAdapter<Summary>() {
    companion object {
        private const val MATCH_TYPE = 1
    }

    fun addItems(items: List<Summary>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

//    override fun bind(binding: ParentRecyclerItemLayoutBinding, item: Summary) {
//
//        binding.rvTeam1.adapter = Team1Adapter().apply {
//            actionTime = { item.actionTime!! }
//            setItems(item.team1Action)
//        }
//        binding.rvTeam1.layoutManager = LinearLayoutManager(binding.root.context)
//
//
//        binding.rvTeam2.adapter = Team2Adapter().apply {
//            actionTime = { item.actionTime!! }
//            setItems(item.team2Action)
//        }
//        binding.rvTeam2.layoutManager = LinearLayoutManager(binding.root.context)
//    }

    override fun getItemViewType(position: Int) = MATCH_TYPE

    override fun setViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when (type) {
            MATCH_TYPE ->
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
        RecyclerView.ViewHolder(binding.root), Binder<Summary> {
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
}