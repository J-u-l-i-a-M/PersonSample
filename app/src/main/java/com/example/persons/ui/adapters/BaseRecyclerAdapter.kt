package com.example.persons.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.persons.ui.holders.BaseViewHolder

abstract class BaseRecyclerAdapter<T>(
    items: List<T> = listOf(),
    private val itemClick: (T) -> Unit = {}
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val items: MutableList<T> = mutableListOf()

    init {
        this.items.addAll(items)
    }

    @LayoutRes
    protected abstract fun getHolderLayout(viewType: Int): Int

    protected abstract fun getHolder(view: View): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getHolder(LayoutInflater.from(parent.context).inflate(getHolderLayout(viewType), parent, false))

    open fun update(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAll() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun getItems(): List<T> = items

    fun getItem(position: Int) = items[position]

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        with(items[position]) {
            holder.itemView.setOnClickListener { itemClick(this) }
            holder.bind(this)
            additionalBindViewHolder(holder, this, position)
        }
    }

    open fun additionalBindViewHolder(holder: BaseViewHolder<T>, item: T, position: Int) {}

    override fun getItemCount() = items.size
}
