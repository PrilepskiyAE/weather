package com.ambrella.weather.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ambrella.weather.Repository.CityRepositoryImpl
import com.ambrella.weather.Repository.RepositoryCity

class CityListViewModelFactory(private val repository: RepositoryCity
):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CityListViewModel(repository as CityRepositoryImpl) as T
    }

}