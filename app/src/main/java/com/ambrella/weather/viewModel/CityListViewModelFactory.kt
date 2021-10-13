package com.ambrella.weather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ambrella.weather.repository.CityRepositoryImpl
import com.ambrella.weather.repository.RepositoryCity

class CityListViewModelFactory(private val repository: RepositoryCity
):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CityListViewModel(repository as CityRepositoryImpl) as T
    }

}