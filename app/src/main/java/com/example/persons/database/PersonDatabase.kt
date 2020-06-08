package com.example.persons.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.persons.database.dao.PersonDao
import com.example.persons.database.dao.PersonSpecDao
import com.example.persons.database.dao.SpecialityDao
import com.example.persons.database.entity.*

@Database(
    version = 1,
    entities = [
        Speciality::class,
        Person::class,
        PersonSpecJoin::class
    ],
    exportSchema = false
)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun specialityDao(): SpecialityDao
    abstract fun personDao(): PersonDao
    abstract fun personSpecDao(): PersonSpecDao

    companion object {
        fun create(context: Context): PersonDatabase =
            Room.databaseBuilder(
                context, PersonDatabase::class.java,
                DATABASE_NAME
            )
                .build()

        private const val DATABASE_NAME = "persondatabase.name"
    }
}