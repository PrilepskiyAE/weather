package com.ambrella.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ambrella.weather.adapters.DailyAdapter
import com.ambrella.weather.adapters.HoursAdapter
import com.ambrella.weather.databinding.FragmentInfoBinding
import com.ambrella.weather.repository.RetrofitRepositoryImpl
import com.ambrella.weather.retrofit.CityApiInterface
import com.ambrella.weather.retrofit.WeatherDetailCity
import com.ambrella.weather.viewModel.RetrofitViewModel
import com.ambrella.weather.viewModel.RetrofitViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    var lon: Double = 0.0
    var lat: Double = 0.0
    lateinit var viewModel: RetrofitViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString("title1")?:throw IllegalArgumentException("Invalid requireArguments")
        binding.tvLabel.text = title
        val retrofitService: CityApiInterface = CityApiInterface.CityApiClient.apiClient()
        val retrofitSercice2: WeatherDetailCity = WeatherDetailCity.CityDetailApiClient.apiClient()

        viewModelFactory = RetrofitViewModelFactory(
            RetrofitRepositoryImpl(retrofitService,retrofitSercice2),
            title,
            binding.root
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(RetrofitViewModel::class.java)
        viewModel.getRequest()
        binding.downfrac.setOnClickListener {
            transitionFrag(view, R.id.mainFragment)
        }

    }

    private fun transitionFrag(view: View, fragnId: Int) {
        val navController = Navigation.findNavController(view)
      //  findNavController().navigate(fragnId, null)
        navController.navigate(fragnId)
    }

}