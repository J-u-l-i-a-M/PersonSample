package com.example.persons.ui.holders

import androidx.annotation.LayoutRes
import android.view.View
import com.example.persons.R
import com.example.persons.database.entity.Person
import com.example.persons.util.DateUtils
import kotlinx.android.synthetic.main.view_item_person.*

class PersonHolder(
    itemView: View,
    private val click: (Person) -> Unit
) : BaseViewHolder<Person>(itemView) {

    override fun bind(item: Person) {
        itemView.setOnClickListener { click(item) }
        first_name.text = item.surname
        last_name.text = item.name
        age.text = DateUtils.getAgeString(itemView.context, item.birthDay)
    }

    companion object {
        @LayoutRes
        const val LAYOUT = R.layout.view_item_person
    }
}