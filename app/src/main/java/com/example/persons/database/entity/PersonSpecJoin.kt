package com.example.persons.database.entity

import androidx.room.*


// вспомогательный класс для связи many-to-many сотрудников и специальностей
@Entity(
    tableName = "spec_person_join",
    primaryKeys = ["specId", "personId"],
    foreignKeys = [
        ForeignKey(entity = Speciality::class,
            parentColumns = ["id"],
            childColumns = ["specId"]),
        ForeignKey(entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["personId"])
    ])
data class PersonSpecJoin(
    val specId: Int,
    val personId: Int
)