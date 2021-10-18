package com.ambrella.weather.repository

import com.ambrella.weather.retrofit.pojo.CurrentWeather
import com.ambrella.weather.retrofit.pojo.DaysWeather
import retrofit2.Call

interface RetrofitRepository {
    suspend fun getWeatherCity(city:String,units:String,lang:String,apiKey:String): Call<CurrentWeather>
    fun getWeatherDetailCity(lat: String,lon: String, exclude: String, units: String, apiKey: String): Call<DaysWeather>
}