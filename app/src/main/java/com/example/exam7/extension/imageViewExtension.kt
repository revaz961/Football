package com.example.exam7.extension

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.exam7.R

fun ImageView.load(url: String?, placeholder:Int = R.drawable.path_8, error: Int= R.drawable.path_8) {
    Glide.with(this.context)
        .load(url?:"")
        .placeholder(placeholder)
        .error(error)
        .into(this);
}