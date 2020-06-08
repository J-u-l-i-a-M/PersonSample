package com.example.persons.presenters

import android.annotation.SuppressLint
import com.example.persons.repository.AppRepository
import moxy.InjectViewState
import javax.inject.Inject
import com.example.persons.util.applySchedulersSingle
import com.example.persons.views.PersonFullView

@InjectViewState
class PersonFullPresenter @Inject constructor(
    private val repository: AppRepository
) : BaseMoxyPresenter<PersonFullView>() {

    @SuppressLint("CheckResult")
    fun loadPerson(personId: Int) {
        repository.personFullInfo(personId)
            .applySchedulersSingle()
            .subscribe(
                { (person, specs) -> viewState.onDataLoaded(person, specs) },
                Throwable::printStackTrace
            )
    }
}