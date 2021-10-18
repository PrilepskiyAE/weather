package com.ambrella.weather.repository

import com.ambrella.weather.retrofit.CityApiInterface
import com.ambrella.weather.retrofit.WeatherDetailCity
import com.ambrella.weather.retrofit.pojo.CurrentWeather
import com.ambrella.weather.retrofit.pojo.DaysWeather
import retrofit2.Call

class RetrofitRepositoryImpl(private val retrofitService: CityApiInterface, private val retrofitService2: WeatherDetailCity):RetrofitRepository {
    override suspend fun getWeatherCity(
        city: String,
        units: String,
        lang: String,
        apiKey: String
    ): Call<CurrentWeather> {

        val call: Call<CurrentWeather> = retrofitService.getWeatherCity(city,units,lang,apiKey)
        return call

    }

    override  fun getWeatherDetailCity(
        lat: String,
        lon: String,
        exclude: String,
        units: String,
        apiKey: String
    ): Call<DaysWeather> {
        val call: Call<DaysWeather> = retrofitService2.getWeatherCityDetail(lat,lon,exclude,units, apiKey)
        return call
    }




}