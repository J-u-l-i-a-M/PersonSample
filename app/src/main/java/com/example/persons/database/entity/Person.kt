package com.example.persons.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.persons.network.entity.PersonResponse
import com.example.persons.util.DateUtils
import com.example.persons.util.StringUtils

// данные о сотруднике
@Entity(tableName = "persons")
data class Person(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "first_name")
    val surname: String,
    @ColumnInfo(name = "last_name")
    val name: String,
    @ColumnInfo(name = "birth_day")
    val birthDay: String,
    @ColumnInfo(name = "image")
    val image: String
) {
    constructor(id: Int, response: PersonResponse) : this(
        id,
        StringUtils.capitalizeFirstLetter(response.firstName ?: ""),
        StringUtils.capitalizeFirstLetter(response.lastName ?: ""),
        DateUtils.convertDate(response.birthDay ?: "-"),
        response.image ?: ""
    )
}