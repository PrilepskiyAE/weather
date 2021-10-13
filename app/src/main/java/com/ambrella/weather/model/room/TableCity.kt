package com.ambrella.weather.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablecity")//здесь лучше через подчерк или с большой буквы
data class TableCity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "cityname") val city:String
)