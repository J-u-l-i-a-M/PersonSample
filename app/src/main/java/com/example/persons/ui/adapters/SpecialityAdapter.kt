package com.example.persons.ui.adapters

import android.view.View
import com.example.persons.database.entity.Speciality
import com.example.persons.ui.holders.BaseViewHolder
import com.example.persons.ui.holders.SpecialityHolder

class SpecialityAdapter(
    private val enableClick: Boolean = false,
    private val click: (Speciality) -> Unit = {}
) : BaseRecyclerAdapter<Speciality>() {

    override fun getHolder(view: View): BaseViewHolder<Speciality> =
        SpecialityHolder(view, enableClick, click)

    override fun getHolderLayout(viewType: Int) = SpecialityHolder.LAYOUT
}