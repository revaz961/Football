package com.example.exam7.adapter.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T>() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return setViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(items[position])
    }

    override fun getItemCount() = items.size

    abstract fun setViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder
}