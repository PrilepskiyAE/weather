package com.ambrella.weather.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*

fun convertTimestapToTime(timestap: Long,pattern:String): String {

    val stamp = Timestamp(timestap * 1000)
    //val stamp = Timestamp(System.currentTimeMillis())
    val date = Date(stamp.time)

    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)


  //return date = Instant.ofEpochSecond(timestap).atZone(ZoneId.systemDefault()).toLocalDateTime().toString()



}
