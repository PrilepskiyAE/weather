package com.ambrella.weather.ViewModel

import androidx.lifecycle.*
import com.ambrella.weather.Model.Room.RoomDatabaseCity
import com.ambrella.weather.Model.Room.tableCity
import com.ambrella.weather.Repository.CityRepositoryImpl
import kotlinx.coroutines.launch

class CityListViewModel(private val repository: CityRepositoryImpl) :
    ViewModel() {
    val data: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        for (city in RoomDatabaseCity.PREPOPULATE_DATA){
            viewModelScope.launch {
                repository.insert(city)
            }
        }

    }

    fun insertCity(city: tableCity)
    {
        showProgress()
        viewModelScope.launch { repository.insert(city) }
        hideProgress()
    }

    fun deleteCity(city: tableCity)
    {
        showProgress()
        viewModelScope.launch {
            repository.delete(city)
        }
        hideProgress()
    }

    fun getAllCity(): LiveData<List<tableCity>>
    {
        val citys: LiveData<List<tableCity>>?
        showProgress()
        citys=repository.getAllCity().asLiveData()
        hideProgress()
        return citys
    }

    private fun showProgress() {
        _dataLoading.value = true
    }
    private fun hideProgress() {
        _dataLoading.value = false
    }



}