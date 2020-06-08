package com.example.persons.ui.fragments

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.persons.di.ApplicationLoader
import com.example.persons.util.DateUtils
import com.example.persons.R
import com.example.persons.di.DaggerAppComponent
import com.example.persons.database.entity.Person
import com.example.persons.database.entity.Speciality
import com.example.persons.presenters.PersonFullPresenter
import com.example.persons.ui.adapters.SpecialityAdapter
import com.example.persons.views.PersonFullView
import kotlinx.android.synthetic.main.fragment_person_full.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PersonFullFragment : BaseFragment(), PersonFullView {

    override fun titleResId() = R.string.person_info

    override fun layoutResId() = R.layout.fragment_person_full

    @Inject
    lateinit var presenterLazy: dagger.Lazy<PersonFullPresenter>
    @InjectPresenter
    lateinit var presenter: PersonFullPresenter
    @ProvidePresenter
    fun provide(): PersonFullPresenter = presenterLazy.get()

    private val adapter: SpecialityAdapter by lazy { SpecialityAdapter() }

    private val personId: Int by lazy { arguments?.getInt(PERSON_ID)  ?: 0 }

    override fun inject() {
        DaggerAppComponent
            .builder()
            .appModule(ApplicationLoader.instance.appModule)
            .build().inject(this)
    }

    override fun initViews() {
        super.initViews()
        presenter.loadPerson(personId)
        recycler_view.adapter = adapter
    }

    override fun onDataLoaded(person: Person, specs: List<Speciality>) {
        first_name.text = person.surname
        last_name.text = person.name
        birth_day.text = String.format(getString(R.string.birth_day), person.birthDay)
        age.text = DateUtils.getAgeString(requireContext(), person.birthDay)

        Glide.with(photo.context)
            .load(person.image)
            .placeholder(R.drawable.ic_person)
            .into(photo)

        adapter.update(specs)
    }

    companion object {
        private const val PERSON_ID = "SPEC_ID"
        fun newInstance(personId: Int) =
            PersonFullFragment().apply {
                arguments = Bundle().apply {
                    putInt(PERSON_ID, personId)
                }
            }
    }
}