package com.ambrella.weather.retrofit.pojo
//посмотри коммент из файла CurrentWeather
data class Weathers(
    val id: Int = 0,
    val main: String,
    val description: String,
    val icon: String
)

data class Current(
    val dt: Long,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: List<Weathers>
)


data class Hourly(
    val dt: Long = 0,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    //var weather: List<Weathers>? = null
    val pop: Double
)

data class Temp(
    var day: Double,
    var min: Double,
    var max: Double,
    var night: Double,
    var eve: Double,
    var morn: Double,
)

data class FeelsLike(
    var day: Double,
    var night: Double,
    var eve: Double,
    var morn: Double,
)

data class Daily(
    var dt: Long,
    var sunrise: Int,
    var sunset: Int,
    var moonrise: Int,
    var moonset: Int,
    var moon_phase: Double,
    var temp: Temp,
    var feels_like: FeelsLike,
    var pressure: Int,
    var humidity: Int,
    var dew_point: Double,
    var wind_speed: Double,
    var wind_deg: Int,
    var wind_gust: Double,
    // var weather: List<Weathers>? = null
    var clouds: Int,
    var pop: Double,
    var uvi: Double,
    var rain: Double
)

data class DaysWeather(
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezone_offset: Int,
    var current: Current,
    var hourly: List<Hourly>,
    var daily: List<Daily>
)
