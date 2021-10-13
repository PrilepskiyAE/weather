package com.ambrella.weather.repository

import com.ambrella.weather.model.room.TableCity
import kotlinx.coroutines.flow.Flow

interface RepositoryCity {
    fun getAllCity(): Flow<List<TableCity>>
    suspend fun insert(city: TableCity)
    suspend fun delete(city: TableCity)
}