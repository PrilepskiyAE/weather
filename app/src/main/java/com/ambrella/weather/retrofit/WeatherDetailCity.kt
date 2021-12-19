package com.ambrella.weather.retrofit

import com.ambrella.weather.retrofit.pojo.DaysWeather
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    ): Call<DaysWeather>

    object CityDetailApiClient {
        const val BASE_URL = "https://api.openweathermap.org"
        var retrofitService: WeatherDetailCity? = null
        fun apiClient():WeatherDetailCity {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                retrofitService=retrofit.create(WeatherDetailCity::class.java)
            }
            return retrofitService?:throw IllegalArgumentException("Invalid network")
        }
    }
}