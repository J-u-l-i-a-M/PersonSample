package com.example.persons.ui.fragments

import android.os.Bundle
import com.example.persons.di.ApplicationLoader
import com.example.persons.R
import com.example.persons.di.DaggerAppComponent
import com.example.persons.database.entity.Person
import com.example.persons.presenters.PersonPresenter
import com.example.persons.ui.AppScreens
import com.example.persons.views.PersonView
import com.example.persons.ui.adapters.PersonAdapter
import kotlinx.android.synthetic.main.fragment_person.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PersonFragment : BaseFragment(),
    PersonView {

    override fun titleResId() = R.string.persons

    override fun layoutResId() = R.layout.fragment_person

    @Inject
    lateinit var presenterLazy: dagger.Lazy<PersonPresenter>

    @InjectPresenter
    lateinit var presenter: PersonPresenter

    @ProvidePresenter
    fun provide(): PersonPresenter = presenterLazy.get()

    private val adapter: PersonAdapter by lazy {
        PersonAdapter { presenter.showPersonFullInfo(it.id) }
    }

    private val specId: Int by lazy { arguments?.getInt(SPEC_ID) ?: 0 }

    override fun inject() {
        DaggerAppComponent
            .builder()
            .appModule(ApplicationLoader.instance.appModule)
            .build().inject(this)
    }

    override fun initViews() {
        super.initViews()
        recycler_view.adapter = adapter
        presenter.loadPerson(specId)
    }

    override fun onDataLoaded(items: List<Person>) {
        adapter.update(items)
    }

    companion object {
        private const val SPEC_ID = "SPEC_ID"
        fun newInstance(specId: Int) =
            PersonFragment().apply {
                arguments = Bundle().apply {
                    putInt(SPEC_ID, specId)
                }
            }
    }
}