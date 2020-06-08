package com.example.persons.database.dao

import androidx.room.*
import com.example.persons.database.entity.Person
import com.example.persons.database.entity.PersonSpecJoin
import com.example.persons.database.entity.Speciality
import io.reactivex.Single

@Dao
abstract class PersonSpecDao :
    BaseDao<PersonSpecJoin> {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("""
        SELECT * FROM persons INNER JOIN spec_person_join ON
        persons.id = spec_person_join.personId WHERE
        spec_person_join.specId = :specId
        """)
    abstract fun personBySpec(specId: Int): Single<List<Person>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("""
        SELECT * FROM specialities INNER JOIN spec_person_join ON
        specialities.id = spec_person_join.specId WHERE
        spec_person_join.personId = :personId
        """)
    abstract fun specByPerson(personId: Int): Single<List<Speciality>>
}