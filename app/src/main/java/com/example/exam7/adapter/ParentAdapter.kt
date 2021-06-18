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

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private val items = mutableListOf<Summary>()

    fun setItems(list: List<Summary>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ParentRecyclerItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ParentRecyclerItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Summary
        fun bind() {
            model = items[adapterPosition]

            val adapterTeam1 =
                ActionAdapter<Team1ItemLayoutBinding>(Team1ItemLayoutBinding::inflate)
            adapterTeam1.actionTime = { model.actionTime!! }
            adapterTeam1.setItems(model.team1Action)
            binding.rvTeam1.adapter = adapterTeam1
            binding.rvTeam1.layoutManager = LinearLayoutManager(binding.root.context)

            val adapterTeam2 =
                ActionAdapter<Team2ItemLayoutBinding>(Team2ItemLayoutBinding::inflate)
            adapterTeam2.actionTime = { model.actionTime!! }
            adapterTeam2.setItems(model.team2Action)
            binding.rvTeam2.adapter = adapterTeam2
            binding.rvTeam2.layoutManager = LinearLayoutManager(binding.root.context)
        }
    }
}