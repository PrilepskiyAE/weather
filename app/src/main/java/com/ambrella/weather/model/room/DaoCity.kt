package com.ambrella.weather.model.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCity {
    @Query("SELECT * from tablecity")

    fun getCity(): Flow<List<TableCity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tableCity: TableCity)

    @Query("DELETE FROM tablecity")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(tableCity: TableCity)

}