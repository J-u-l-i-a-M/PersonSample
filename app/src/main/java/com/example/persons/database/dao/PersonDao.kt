package com.example.persons.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.persons.database.entity.Person
import io.reactivex.Single

@Dao
abstract class PersonDao : BaseDao<Person> {
    @Query("select * from persons where id = :personId")
    abstract fun getById(personId: Int): Single<Person>
}