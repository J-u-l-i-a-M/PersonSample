package com.example.persons.di

import androidx.multidex.MultiDexApplication

class ApplicationLoader : MultiDexApplication() {
    lateinit var appModule: AppModule

    override fun onCreate() {
        super.onCreate()

        appModule = AppModule(this)
    }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationLoader
            private set
    }
}