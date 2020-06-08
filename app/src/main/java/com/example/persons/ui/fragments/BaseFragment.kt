package com.example.persons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.persons.views.BaseMoxyView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseMoxyView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResId(), container, false)

    @LayoutRes
    protected open fun layoutResId(): Int = 0

    @StringRes
    protected open fun titleResId(): Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initViews()

        if (titleResId() != 0)
            (activity as? AppCompatActivity)?.supportActionBar?.setTitle(titleResId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    protected open fun inject() {

    }

    protected open fun initViews() {

    }
}