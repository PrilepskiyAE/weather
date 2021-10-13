package com.ambrella.weather.retrofit

import com.ambrella.weather.retrofit.pojo.CurrentWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiInterface {//а на фига два apiClient а?

   @GET("/data/2.5/weather")
   fun getWeatherCity(
       @Query("q") city: String,
       @Query("units") units: String,
       @Query("lang") lang: String,
       @Query("appid") apiKey: String
   ): Single<CurrentWeather>
}