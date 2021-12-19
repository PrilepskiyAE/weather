package com.ambrella.weather.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TableCity::class], version = 1, exportSchema = false)
abstract class RoomDatabaseCity : RoomDatabase() {
    abstract fun daoCity(): DaoCity

    companion object {
        private var INSTANCE: RoomDatabaseCity? = null
        fun getInstance(context: Context): RoomDatabaseCity? {
            if (INSTANCE == null) {
                synchronized(RoomDatabaseCity::class)
                {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RoomDatabaseCity::class.java, "best_city_database"
                    ).build()
                }
            }
            return INSTANCE
        }
/*
        val PREPOPULATE_DATA = listOf(
            TableCity(1, "testing1"),
            TableCity(2, "testing2")
        )

 */
    }
}