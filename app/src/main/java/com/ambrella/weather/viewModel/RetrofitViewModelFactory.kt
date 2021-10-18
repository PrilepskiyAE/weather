package com.ambrella.weather.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ambrella.weather.repository.RetrofitRepository
import com.ambrella.weather.repository.RetrofitRepositoryImpl

class RetrofitViewModelFactory(private val repository: RetrofitRepository, private val City:String, private val view: View) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RetrofitViewModel(view,City,repository as RetrofitRepositoryImpl) as T
    }

}