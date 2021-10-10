package com.ambrella.weather.retrofit

import com.ambrella.weather.pojo.CurrentWeather
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiInterface {
   @GET("/data/2.5/weather")
   fun getWeatherCity(
       @Query("q") city: String,
       @Query("units") units: String,
       @Query("lang") lang: String,
       @Query("appid") apiKey: String
   ): Single<CurrentWeather>
}