package com.ambrella.weather.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CityDetailApiClient {
    const val BASE_URL = "https://api.openweathermap.org"
    val apiClientDetail: WeatherDetailCity by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(WeatherDetailCity::class.java)
    }
}

