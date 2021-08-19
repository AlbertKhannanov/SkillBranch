package ru.skillbranch.gameofthrones.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)
    @Delete
    suspend fun deleteAll(entities: List<T>)
}
