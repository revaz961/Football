package com.example.exam7.extension

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.exam7.R

fun ImageView.load(url: String?, placeholder:Int = R.drawable.image_not_found, error: Int= R.drawable.image_not_found) {
    Glide.with(this.context)
        .load(url?:"")
        .placeholder(placeholder)
        .error(error)
        .into(this);
}