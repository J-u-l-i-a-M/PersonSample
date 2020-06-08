package com.example.persons.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visibility(show: Boolean) {
    val newState = if (show) VISIBLE else GONE
    val oldState = this.visibility
    if (newState != oldState) this.visibility = newState
}
