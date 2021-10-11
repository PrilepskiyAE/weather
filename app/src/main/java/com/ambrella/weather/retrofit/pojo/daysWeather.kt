package com.ambrella.weather.retrofit.pojo

data class Weathers(
    var id:Int = 0,
    var main: String,
    var description: String,
    var icon: String
)

data class Current (
    var dt:Long,
    var sunrise:Int,
    var sunset:Int,
    var temp:Double,
    var feels_like:Double,
    var pressure:Int,
    var humidity:Int,
    var dew_point:Double,
    var uvi:Double,
    var clouds:Int,
    var visibility:Int,
    var wind_speed:Double,
    var wind_deg:Int,
    var weather: List<Weathers>
)



data class Hourly (
    var dt:Long = 0,
    var temp :Double,
    var feels_like:Double,
    var pressure:Int,
    var humidity:Int,
    var dew_point:Double,
    var uvi:Double,
    var clouds:Int,
    var visibility:Int,
    var wind_speed:Double,
    var wind_deg:Int,
    var wind_gust :Double,
    //var weather: List<Weathers>? = null
    var pop:Double
)

data class Temp (
    var day:Double,
    var min:Double,
    var max:Double,
    var night:Double,
    var eve:Double,
    var morn:Double,
)

data class FeelsLike (
    var day:Double,
    var night:Double,
    var eve:Double,
    var morn:Double,
)

data class Daily (
    var dt:Long,
    var sunrise:Int,
    var sunset :Int,
    var moonrise:Int,
    var moonset :Int,
    var moon_phase:Double,
    var temp: Temp,
    var feels_like: FeelsLike,
    var pressure :Int,
    var humidity :Int,
    var dew_point:Double,
    var wind_speed :Double,
    var wind_deg :Int,
    var wind_gust:Double,
   // var weather: List<Weathers>? = null
    var clouds :Int,
    var pop :Double,
    var uvi :Double,
    var rain :Double
)

data class daysWeather (
    var lat:Double,
    var lon:Double,
    var timezone: String,
    var timezone_offset:Int,
    var current: Current,
    var hourly: List<Hourly>,
    var daily: List<Daily>
)
