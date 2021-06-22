package com.example.exam7.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormat(pattern: String): String {
    val date = Date(this)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}