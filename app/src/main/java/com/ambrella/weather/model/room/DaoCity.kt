package com.ambrella.weather.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCity {
    @Query("SELECT * from tablecity")
    // при исправлении List на Set получаю ошибку error:
    // Not sure how to convert a Cursor to this method's return type
    // (kotlinx.coroutines.flow.Flow<java.util.Set<com.ambrella.weather.model.room.TableCity>>).
    // public abstract kotlinx.coroutines.flow.Flow<java.util.Set<com.ambrella.weather.model.room.TableCity>>
    // getCity();
    //Не понял как исправить
    fun getCity(): Flow<List<TableCity>>//здесь в списке городов можно set использовать так как у тебя одинаковых городов быть не может

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tableCity: TableCity)

    @Query("DELETE FROM tablecity")
    suspend fun deleteAll()
}