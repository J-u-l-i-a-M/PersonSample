package com.example.persons.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// данные о специальности
@Entity(tableName = "specialities")
data class Speciality(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "spec_name")
    val name: String
)
