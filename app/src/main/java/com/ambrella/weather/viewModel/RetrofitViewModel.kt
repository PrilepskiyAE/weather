package com.ambrella.weather.viewModel

import com.ambrella.weather.adapters.DailyAdapter
import com.ambrella.weather.adapters.HoursAdapter
import com.ambrella.weather.repository.RetrofitRepositoryImpl
import com.ambrella.weather.retrofit.pojo.DaysWeather
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.ambrella.weather.R
import com.ambrella.weather.retrofit.pojo.CurrentWeather
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel(val view: View,val title:String,val retrofitRepository: RetrofitRepositoryImpl) : ViewModel()
{
    var job: Job? = null

    fun getRequest() {
        job = CoroutineScope(Dispatchers.IO ).launch {

            val call=retrofitRepository.getWeatherCity(title.toString(),"metric", "ru","a356083d281fd41b8cc084604cfea1ab")

            withContext(Dispatchers.Main) {

                call.enqueue(object : Callback<CurrentWeather> {
                    override fun onResponse(
                        call: Call<CurrentWeather>,
                        response: Response<CurrentWeather>
                    ) {

                        // Получаем результат
                        //val weathers = response.body()!!.main?.temp?.toDouble().toString()
                        val lon = response.body()?.coord?.lon
                        val lat = response.body()?.coord?.lat
                        val temp: String = response.body()?.main?.temp.toString()
                        val desc: String = response.body()?.weather?.get(0)?.description ?: { view.textView2.text =
                            "xz" }.toString()
                        if (!(response.body()?.main?.temp.toString() == null || response.body()?.weather?.get(0)?.description== null)){
                            view.textView.text = temp
                            view.textView2.text = desc
                           // Glide.with(itemView)
                           // view.imageView.Glide
                        }else
                        {
                            view.textView.text="Введенный вами город не найден"
                            view.textView2.text = "Повторите попытку"
                        }
                        Log.d("TAGS", "широта: " + lon + " Долгота: " + lat)

                        val call2 = retrofitRepository.getWeatherDetailCity(
                            lat.toString(),
                            lon.toString(),
                            "minutely,alerts",
                            "metric",
                            "a356083d281fd41b8cc084604cfea1ab"
                        )



                        call2.enqueue(object : Callback<DaysWeather> {
                            override fun onResponse(
                                call: Call<DaysWeather>,
                                response: Response<DaysWeather>
                            ) {
                                val test = response.body()?.current?.temp
                                Log.d("TAGS", test.toString())
                                val daily = response.body()?.daily
                                view.rvDaily.adapter = daily?.let { DailyAdapter(it, R.layout.item_day) }
                                val hourly = response.body()?.hourly
                                view.rvHourly.adapter = hourly?.let {
                                   HoursAdapter(
                                        it,
                                        R.layout.item_hour
                                    )
                                }
                            }

                            override fun onFailure(call: Call<DaysWeather>, t: Throwable) {
                                Log.e("TAGS", t.toString())
                            }

                        })




                    }

                    override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                        // Log error here since request failed
                        Log.e("TAGS", t.toString())
                    }
                })

            }
        }
    }
}
