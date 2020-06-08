package com.example.persons.di

import com.example.persons.ui.activity.MainActivity
import com.example.persons.ui.fragments.PersonFragment
import com.example.persons.ui.fragments.PersonFullFragment
import com.example.persons.ui.fragments.SpecialityFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: SpecialityFragment)
    fun inject(fragment: PersonFragment)
    fun inject(fragment: PersonFullFragment)
}
