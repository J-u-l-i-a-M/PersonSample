package com.example.persons.di

import android.content.Context
import com.example.persons.database.PersonDatabase
import com.example.persons.repository.AppRepository
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule(
    @get:Provides val context: Context
) {
    // база данных
    private val database: PersonDatabase by lazy { PersonDatabase.create(context) }

    @get:Provides
    @Singleton
    val appRepository: AppRepository by lazy { AppRepository(database) }

    // Cicerone для навигации
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigationHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    @get:Provides
    val router: Router by lazy { cicerone.router }
}