package com.example.moviedbapp.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.changeDateFormat(formatOld: String, formatNew: String): String {
    if(this.isEmpty()) return "-"

    val dateFormatOld = SimpleDateFormat(formatOld)
    val d: Date = dateFormatOld.parse(this) as Date
    val dateFormatNew = SimpleDateFormat(formatNew)
    return dateFormatNew.format(d)
}