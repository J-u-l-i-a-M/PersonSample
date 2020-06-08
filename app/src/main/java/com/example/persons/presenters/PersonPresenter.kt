package com.example.persons.presenters

import android.annotation.SuppressLint
import com.example.persons.di.ApplicationLoader
import com.example.persons.repository.AppRepository
import com.example.persons.ui.AppScreens
import moxy.InjectViewState
import javax.inject.Inject
import com.example.persons.util.applySchedulersSingle
import com.example.persons.views.PersonView
import ru.terrakok.cicerone.Router

@InjectViewState
class PersonPresenter @Inject constructor(
    private val repository: AppRepository,
    private val router: Router
) : BaseMoxyPresenter<PersonView>() {

   @SuppressLint("CheckResult")
   fun loadPerson(specId: Int) {
        repository.personBySpec(specId)
            .applySchedulersSingle()
            .subscribe(viewState::onDataLoaded) { it.printStackTrace() }
    }

    fun showPersonFullInfo(personId: Int) {
        router.navigateTo(
            AppScreens.PersonFullFragmentScreen(
                personId
            )
        )
    }
}