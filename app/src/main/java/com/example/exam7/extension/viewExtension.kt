package com.example.exam7.extension

import android.view.View

fun View.visible(visible:Boolean){
    if(visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}