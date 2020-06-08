package com.example.persons.presenters

import android.annotation.SuppressLint
import com.example.persons.repository.AppRepository
import com.example.persons.ui.AppScreens
import com.example.persons.util.applySchedulersCompletable
import com.example.persons.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val repository: AppRepository,
    private val router: Router
) : BaseMoxyPresenter<MainView>() {

    @SuppressLint("CheckResult")
    fun loadData() {
        repository.loadData()
            .applySchedulersCompletable()
            .subscribe({
                viewState.onDataLoaded(true)
                router.newRootScreen(AppScreens.SpecialityFragmentScreen())
            }, { viewState.onDataLoaded(false) })
    }
}
