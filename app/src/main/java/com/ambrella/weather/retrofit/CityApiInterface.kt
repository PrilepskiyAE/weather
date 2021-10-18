package com.ambrella.weather.retrofit

import com.ambrella.weather.retrofit.pojo.CurrentWeather

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiInterface {


    @GET("/data/2.5/weather")
    fun getWeatherCity(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") apiKey: String
    ):Call<CurrentWeather>

    object CityApiClient {
        const val BASE_URL = "https://api.openweathermap.org"
        var retrofitService: CityApiInterface? = null

        fun apiClient(): CityApiInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                retrofitService=retrofit.create(CityApiInterface::class.java)
            }
            return retrofitService!!
        }}}


