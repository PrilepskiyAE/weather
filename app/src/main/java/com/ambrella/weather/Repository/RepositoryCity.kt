package com.ambrella.weather.Repository

import com.ambrella.weather.Model.Room.tableCity
import kotlinx.coroutines.flow.Flow

interface RepositoryCity {
    fun getAllCity(): Flow<List<tableCity>>
    suspend fun insert(city: tableCity)
    suspend fun delete(city: tableCity)
}