package com.example.persons.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(
    override val containerView: View
) : ViewHolder(containerView), LayoutContainer {
    open fun bind(item: T) {}
}
