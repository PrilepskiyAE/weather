package com.ambrella.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ambrella.weather.adapters.DailyAdapter
import com.ambrella.weather.adapters.HoursAdapter
import com.ambrella.weather.databinding.FragmentInfoBinding
import com.ambrella.weather.retrofit.CityApiClient
import com.ambrella.weather.retrofit.CityDetailApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    var lon: Double = 0.0
    var lat: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString("title1")
        binding.tvLabel.setText(title) // используй котлин
        val call = CityApiClient.apiClient.getWeatherCity(
            title.toString(),
            "metric",
            "ru",
            "a356083d281fd41b8cc084604cfea1ab"
        )
        call// а на хрена RX?
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                val lon = it.coord.lon
                val lat = it.coord.lat
                var temp = it.main?.temp
                val desc = it.weather[0].description.toString()

                binding.textView.setText(temp.toString())
                binding.textView2.setText(desc)
                Log.d("TAGS", "широта: " + lon + " Долгота: " + lat)
                val call2 = CityDetailApiClient.apiClientDetail.getWeatherCityDetail(
                    lat.toString(),
                    lon.toString(),
                    "minutely,alerts",
                    "metric",
                    "a356083d281fd41b8cc084604cfea1ab"
                )
                call2
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // val test=it.current?.temp
                        val daily = it.daily
                        binding.rvDaily.adapter = DailyAdapter(daily, R.layout.item_day)
                        val hourly = it.hourly
                        binding.rvHourly.adapter = HoursAdapter(hourly, R.layout.item_hour)
                    }, {// Логируем ошибку
                            error ->
                        Log.e("TAGS2", error.toString())
                    })

            },
                { error ->
                    // Логируем ошибку
                    Log.e("TAGS1", error.toString())
                    binding.textView.setText("Введенный вами Город не найден")

                })

        /*
        enqueue(object : Callback<CurrentWeather> {
            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                // Получаем результат
                //val weathers = response.body()!!.main?.temp?.toDouble().toString()
                lon= response.body()!!.coord.lon
                lat=response.body()!!.coord.lat
                val temp:String= response.body()!!.main?.temp.toString()
                var desc:String= response.body()!!.weather[0].description.toString()
                binding.textView.setText(temp)
                binding.textView2.setText(desc)
               Log.d("TAGS", "широта: "+lon+" Долгота: "+lat)

                val call2= CityDetailApiClient.apiClientDetail.getWeatherCityDetail(
                    lat.toString(),
                    lon.toString(),
                    "minutely,alerts",
                    "metric",
                    "a356083d281fd41b8cc084604cfea1ab")



                call2.enqueue(object : Callback<daysWeather>
                {
                    override fun onResponse(call: Call<daysWeather>, response: Response<daysWeather>) {
                        val test=response.body()!!.current?.temp
                        Log.d("TAGS", test.toString())
                        val daily = response.body()!!.daily
                        binding.rvDaily.adapter=dailyAdapter(daily,R.layout.item_day)
                        val hourly = response.body()!!.hourly
                        binding.rvHourly.adapter=hourlyAdapter(hourly,R.layout.item_hour)
                    }

                    override fun onFailure(call: Call<daysWeather>, t: Throwable) {
                        Log.e("TAGS", t.toString())
                    }

                })


            }
            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                // Log error here since request failed
                Log.e("TAGS", t.toString())
            }
        })

         */
        binding.downfrac.setOnClickListener {
            val navController = Navigation.findNavController(view)
            findNavController().navigate(R.id.mainFragment, null)
            navController.navigate(R.id.mainFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment() //на фига если не используешь
    }
}