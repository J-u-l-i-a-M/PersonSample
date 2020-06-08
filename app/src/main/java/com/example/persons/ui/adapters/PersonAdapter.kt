package com.example.persons.ui.adapters

import android.view.View
import com.example.persons.database.entity.Person
import com.example.persons.ui.holders.BaseViewHolder
import com.example.persons.ui.holders.PersonHolder

class PersonAdapter(
    private val click: (Person) -> Unit
) : BaseRecyclerAdapter<Person>() {

    override fun getHolder(view: View): BaseViewHolder<Person> =
        PersonHolder(view, click)

    override fun getHolderLayout(viewType: Int) = PersonHolder.LAYOUT
}