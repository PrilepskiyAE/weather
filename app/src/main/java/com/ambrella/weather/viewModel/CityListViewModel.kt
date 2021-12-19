package com.ambrella.weather.viewModel

import androidx.lifecycle.*
import com.ambrella.weather.model.room.RoomDatabaseCity
import com.ambrella.weather.model.room.TableCity
import com.ambrella.weather.repository.CityRepositoryImpl
import kotlinx.coroutines.launch

class CityListViewModel(private val repository: CityRepositoryImpl) : ViewModel() {
    val data: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    //private val _dataLoading = MutableLiveData<Boolean>()
   // val dataLoading: LiveData<Boolean> = _dataLoading

    /*init {
        for (city in RoomDatabaseCity.PREPOPULATE_DATA) {
            viewModelScope.launch {
                repository.insert(city)
            }
        }

    }

     */

    fun insertCity(city: TableCity) {
      //  showProgress()
        viewModelScope.launch { repository.insert(city) }
      //  hideProgress()
    }

    fun deleteCity(city: TableCity) {
   //     showProgress()
        viewModelScope.launch {
            repository.delete(city)
        }
            // hideProgress()
    }

    fun getAllCity(): LiveData<List<TableCity>> {
        val citys: LiveData<List<TableCity>>?
      //  showProgress()
        citys = repository.getAllCity().asLiveData()
  //      hideProgress()
        return citys
    }

    //private fun showProgress() {
   //     _dataLoading.value = true
   // }

    //private fun hideProgress() {
   //     _dataLoading.value = false
   // }


}