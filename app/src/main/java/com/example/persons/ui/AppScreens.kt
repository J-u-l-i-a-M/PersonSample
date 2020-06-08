package com.example.persons.ui

import com.example.persons.ui.fragments.PersonFragment
import com.example.persons.ui.fragments.PersonFullFragment
import com.example.persons.ui.fragments.SpecialityFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AppScreens {
    class SpecialityFragmentScreen() : SupportAppScreen() {
        override fun getFragment() = SpecialityFragment()
    }

    class PersonFragmentScreen(private val specId: Int) : SupportAppScreen() {
        override fun getFragment() = PersonFragment.newInstance(specId)
    }

    class PersonFullFragmentScreen(private val personId: Int) : SupportAppScreen() {
        override fun getFragment() = PersonFullFragment.newInstance(personId)
    }
}