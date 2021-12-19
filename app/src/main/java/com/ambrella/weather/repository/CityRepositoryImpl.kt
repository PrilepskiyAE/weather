package com.ambrella.weather.repository

import android.content.Context
import com.ambrella.weather.model.room.DaoCity
import com.ambrella.weather.model.room.RoomDatabaseCity
import com.ambrella.weather.model.room.TableCity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CityRepositoryImpl(context: Context, private val backgroundDispatcher: CoroutineDispatcher) :
    RepositoryCity {
    private val taskDao: DaoCity

    init {
        val database = RoomDatabaseCity.getInstance(context)?:throw IllegalArgumentException("Invalid database")
        taskDao = database.daoCity()
    }

    override fun getAllCity(): Flow<List<TableCity>> {
        return taskDao.getCity()
    }

    override suspend fun insert(city: TableCity) {
        withContext(backgroundDispatcher)
        {
            taskDao.insert(city)
        }
    }

    override suspend fun delete(city: TableCity) {
        withContext(backgroundDispatcher) {
            taskDao.delete(city)
        }
    }
}