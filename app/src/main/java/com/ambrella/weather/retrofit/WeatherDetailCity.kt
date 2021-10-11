package com.ambrella.weather.retrofit

import com.ambrella.weather.retrofit.pojo.daysWeather
import io.reactivex.Single
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
    ): Single<daysWeather>
}