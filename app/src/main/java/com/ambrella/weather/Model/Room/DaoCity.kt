package com.ambrella.weather.Model.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCity {
    @Query("SELECT * from tablecity")
    //fun getCityList(): LiveData<List<tableCity>>
    fun getCity(): Flow<List<tableCity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tableCity:tableCity)

    @Query("DELETE FROM tablecity")
    suspend fun deleteAll()
}