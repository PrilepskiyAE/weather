package com.ambrella.weather.retrofit

import com.ambrella.weather.pojo.CurrentWeather
import com.ambrella.weather.pojo.daysWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDetailCity {
    @GET("/data/2.5/onecall")
    fun getWeatherCityDetail(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<daysWeather>
}