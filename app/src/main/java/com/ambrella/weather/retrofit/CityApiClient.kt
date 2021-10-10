package com.ambrella.weather.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CityApiClient {
    const val BASE_URL = "https://api.openweathermap.org"
    val apiClient: CityApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(CityApiInterface::class.java)
    }
}