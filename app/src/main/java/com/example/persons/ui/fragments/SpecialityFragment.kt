package com.example.persons.ui.fragments

import com.example.persons.di.ApplicationLoader
import com.example.persons.R
import com.example.persons.di.DaggerAppComponent
import com.example.persons.database.entity.Speciality
import com.example.persons.presenters.SpecialityPresenter
import com.example.persons.ui.AppScreens
import com.example.persons.views.SpecialityView
import com.example.persons.ui.adapters.SpecialityAdapter
import kotlinx.android.synthetic.main.fragment_speciality.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SpecialityFragment : BaseFragment(),
    SpecialityView {

    override fun titleResId() = R.string.specialities

    override fun layoutResId() = R.layout.fragment_speciality

    @Inject
    lateinit var presenterLazy: dagger.Lazy<SpecialityPresenter>

    @InjectPresenter
    lateinit var presenter: SpecialityPresenter

    @ProvidePresenter
    fun provide(): SpecialityPresenter = presenterLazy.get()

    private val adapter: SpecialityAdapter by lazy {
        SpecialityAdapter(true) {
            presenter.showPersons(it.id)
        }
    }

    override fun inject() {
        DaggerAppComponent
            .builder()
            .appModule(ApplicationLoader.instance.appModule)
            .build().inject(this)
    }

    override fun initViews() {
        super.initViews()
        recycler_view.adapter = adapter
    }

    override fun onDataLoaded(items: List<Speciality>) {
        adapter.update(items)
    }
}