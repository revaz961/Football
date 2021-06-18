package com.example.exam7.extension

import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.textColor(id:Int){
    this.setTextColor(ContextCompat.getColor(this.context,id))
}