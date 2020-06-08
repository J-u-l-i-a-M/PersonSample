package com.example.persons.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable

internal interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIfNotExists(roomEntities: Collection<T>) : Completable
}