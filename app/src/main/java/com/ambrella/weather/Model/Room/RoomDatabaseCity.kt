package com.ambrella.weather.Model.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineDispatcher

@Database(entities = arrayOf(tableCity::class),version = 1,exportSchema = false)
abstract class RoomDatabaseCity: RoomDatabase() {
    abstract fun daoCity():DaoCity

    companion object{
        private var INSTANCE:RoomDatabaseCity?=null
        fun getInstance(context: Context, backgroundDispatcher: CoroutineDispatcher):RoomDatabaseCity?
        {
            if (INSTANCE==null){
                synchronized(RoomDatabaseCity::class)
                {
                    INSTANCE= Room.databaseBuilder(
                        context,
                        RoomDatabaseCity::class.java,"best_city_database"
                    ).build()
                }
            }
            return INSTANCE
        }

        val PREPOPULATE_DATA= listOf(
            tableCity(1,"testing1"),
            tableCity(2,"testing2")
        )

    }

}