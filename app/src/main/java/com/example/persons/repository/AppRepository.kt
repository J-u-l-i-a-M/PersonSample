package com.example.persons.repository

import com.example.persons.database.PersonDatabase
import com.example.persons.database.entity.Person
import com.example.persons.database.entity.PersonSpecJoin
import com.example.persons.database.entity.Speciality
import com.example.persons.network.PersonApiService
import com.example.persons.network.PersonApi
import com.example.persons.network.entity.PersonResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class AppRepository(
    private val database: PersonDatabase
) {
    private val service by lazy { PersonApi.client.create(PersonApiService::class.java) }

    // загрузка данных
    fun loadData(): Completable =
        service.getPerson()
            .flatMapCompletable {
                insertData(it.persons ?: listOf())
            }

    // список специальностей
    fun specialities() =
        database.specialityDao().all()

    // список сотрудников по этой специальности
    fun personBySpec(specId: Int) =
        database.personSpecDao().personBySpec(specId)

    // список данных о сотруднике в формате (сотрудник, список специальностей)
    fun personFullInfo(personId: Int): Single<Pair<Person, List<Speciality>>> =
        Single.zip(
            database.personDao().getById(personId),
            database.personSpecDao().specByPerson(personId),
            BiFunction { person, specList -> person to specList }
        )

    // сохранение данных в  БД
    private fun insertData(persons: List<PersonResponse>): Completable =
        database
            .specialityDao()
            .insertIfNotExists(
                persons.asSequence().map { it.specList ?: listOf() }.toList().flatten().distinct()
                    .map { Speciality(it.specId, it.specName ?: "") }.toList()
            )
            .andThen(
                database
                    .personDao()
                    .insertIfNotExists(persons.mapIndexed { index, response ->
                        Person(
                            index,
                            response
                        )
                    }.toList())
            ).andThen(
                database
                    .personSpecDao()
                    .insertIfNotExists(generate(persons))
            )

    private fun generate(persons: List<PersonResponse>): List<PersonSpecJoin> {
        val joinList = mutableListOf<PersonSpecJoin>()
        persons.forEachIndexed { index, person ->
            person.specList?.forEach {
                joinList += PersonSpecJoin(it.specId, index)
            }
        }
        return joinList
    }
}