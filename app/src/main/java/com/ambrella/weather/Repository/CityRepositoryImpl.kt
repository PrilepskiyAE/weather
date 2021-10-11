package com.ambrella.weather.Repository

import android.content.Context
import com.ambrella.weather.Model.Room.DaoCity
import com.ambrella.weather.Model.Room.RoomDatabaseCity
import com.ambrella.weather.Model.Room.tableCity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CityRepositoryImpl(context: Context, private val backgroundDispatcher: CoroutineDispatcher):RepositoryCity
{
    private val taskDao: DaoCity
    init {
        val database = RoomDatabaseCity.getInstance(context, backgroundDispatcher)
        taskDao = database!!.daoCity()
    }

    override fun getAllCity(): Flow<List<tableCity>> {
        return taskDao.getCity()
    }

    override suspend fun insert(city: tableCity) {
        withContext(backgroundDispatcher)
        {
            taskDao.insert(city)
        }
    }

    override suspend fun delete(city: tableCity) {
        withContext(backgroundDispatcher){
            taskDao.deleteAll()
        }
    }

}