package com.example.persons.ui.holders

import android.graphics.Typeface
import androidx.annotation.LayoutRes
import android.view.View
import com.example.persons.R
import com.example.persons.database.entity.Speciality
import com.example.persons.util.visibility
import kotlinx.android.synthetic.main.view_item_speciality.*

class SpecialityHolder(
    itemView: View,
    private val enableClick: Boolean,
    private val click: (Speciality) -> Unit
) : BaseViewHolder<Speciality>(itemView) {

    override fun bind(item: Speciality) {
        itemView.setOnClickListener { click(item) }
        name.text = item.name
        icon.visibility(enableClick)
        separate.visibility(enableClick)
        name.setTypeface(null, if (enableClick) Typeface.BOLD else Typeface.NORMAL)
    }

    companion object {
        @LayoutRes
        const val LAYOUT = R.layout.view_item_speciality
    }
}