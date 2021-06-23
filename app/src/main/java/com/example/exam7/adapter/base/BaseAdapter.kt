package com.example.exam7.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.exam7.model.Match

typealias GetMatchInfo = () -> Match

abstract class BaseAdapter<T>() :
    RecyclerView.Adapter<BaseViewHolder<T,ViewBinding>>() {

    protected val items = mutableListOf<T>()

    override fun onBindViewHolder(holder: BaseViewHolder<T,ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}