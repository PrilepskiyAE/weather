package com.ambrella.weather.retrofit.pojo

// и под каждый класс лучше отдельный файл заводить
data class CurrentWeather (
    val  coord: Coord,
    val weather: List<Weather>,
  //  var base: String? = null,
   val main: Main
   // var visibility:Int = 0,
   // var wind: Wind? = null,
   // var clouds: Clouds? = null,
   // var dt:Int = 0,
   // var sys: Sys? = null,
   // var timezone:Int = 0,
   // var id:Int = 0,
   // var name: String? = null,
   // var cod:Int = 0
)


data class Coord (
    var lon:Double = 0.0,
    var lat:Double = 0.0
)

data class Weather (
    val id:Int = 0,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp:Double = 0.0,
    val feels_like:Double = 0.0,
    val temp_min:Double = 0.0,
    val temp_max:Double = 0.0,
    val pressure:Int= 0,
    val humidity:Int = 0
)
/*
data class Wind (
    var speed:Double = 0.0,
    var deg:Int = 0
)

data class Clouds (
    var all:Int = 0
)

data class Sys(
    var type:Int = 0,
    var id:Int = 0,
    var country: String? = null,
    var sunrise:Int = 0,
    var sunset:Int = 0

)

 */
