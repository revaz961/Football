package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.exam7.fragment.Inflate

typealias GetViewHolder<VH,VB> = (VB) -> VH

abstract class BaseAdapter<T, VB : ViewBinding, VH : RecyclerView.ViewHolder>(val inflate: Inflate<VB>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract val items: MutableList<T>

    abstract val getViewHolder:GetViewHolder<VH,VB>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = items.size
}