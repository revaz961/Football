package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam7.databinding.ParentRecyclerItemLayoutBinding
import com.example.exam7.databinding.Team1ItemLayoutBinding
import com.example.exam7.databinding.Team2ItemLayoutBinding
import com.example.exam7.model.Summary

class MatchAdapter :
    BaseAdapter<Summary, ParentRecyclerItemLayoutBinding>(ParentRecyclerItemLayoutBinding::inflate) {

    override fun bind(binding: ParentRecyclerItemLayoutBinding, item: Summary) {

        binding.rvTeam1.adapter = Team1Adapter().apply {
            actionTime = { item.actionTime!! }
            setItems(item.team1Action)
        }
        binding.rvTeam1.layoutManager = LinearLayoutManager(binding.root.context)


        binding.rvTeam2.adapter = Team2Adapter().apply {
            actionTime = { item.actionTime!! }
            setItems(item.team2Action)
        }
        binding.rvTeam2.layoutManager = LinearLayoutManager(binding.root.context)
    }
}