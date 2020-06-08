package com.example.persons.presenters

import android.annotation.SuppressLint
import com.example.persons.di.ApplicationLoader
import com.example.persons.util.applySchedulersSingle
import com.example.persons.repository.AppRepository
import com.example.persons.ui.AppScreens
import com.example.persons.views.SpecialityView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SpecialityPresenter @Inject constructor(
    private val repository: AppRepository,
    private val router: Router
) : BaseMoxyPresenter<SpecialityView>() {

    @SuppressLint("CheckResult")
    override fun attachView(view: SpecialityView) {
        super.attachView(view)
        repository.specialities()
            .applySchedulersSingle()
            .subscribe(viewState::onDataLoaded, Throwable::printStackTrace)
    }

    fun showPersons(specId: Int) {
        router.navigateTo(
            AppScreens.PersonFragmentScreen(
                specId
            )
        )
    }
}


