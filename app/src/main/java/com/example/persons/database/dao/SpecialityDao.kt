package com.example.persons.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.persons.database.entity.Speciality
import io.reactivex.Single

@Dao
abstract class SpecialityDao :
    BaseDao<Speciality> {
    @Query("select * from specialities")
    internal abstract fun all(): Single<List<Speciality>>
}
