package com.ambrella.weather.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestapToTime(timestap: Long?): String {// здесь по идее на вход не должно нулл падать паттерны в отдельные константы, и вообще подумай как этот код упростить
    if (timestap == null) return ""
    val stamp = Timestamp(timestap * 1000)//умножение на тысячу выглядит как костыль
    val date = Date(stamp.time)
    val pattern = "HH:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun convertTimestapToDateTime(timestap: Long?): String {
    if (timestap == null) return ""
    val stamp = Timestamp(timestap * 1000)
    val date = Date(stamp.time)
    val pattern = "d.M.y"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}