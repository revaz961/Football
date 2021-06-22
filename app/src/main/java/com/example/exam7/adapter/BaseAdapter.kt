package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.exam7.fragment.Inflate


abstract class BaseAdapter<T, VB : ViewBinding>(val inflate: Inflate<VB>) :
    RecyclerView.Adapter<BaseAdapter<T, VB>.BaseHolder>() {

    private val items = mutableListOf<T>()

    fun setItems(items: List<T>?) {
        if (!items.isNullOrEmpty()) {
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = items.size

    abstract fun bind(binding: VB, item: T)

    inner class BaseHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            bind(binding, items[adapterPosition])
        }
    }
}