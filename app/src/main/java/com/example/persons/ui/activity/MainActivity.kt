package com.example.persons.ui.activity

import android.os.Bundle
import android.view.View
import com.example.persons.R
import com.example.persons.di.DaggerAppComponent
import com.example.persons.presenters.MainPresenter
import com.example.persons.ui.AppScreens
import com.example.persons.di.ApplicationLoader
import com.example.persons.util.visibility
import com.example.persons.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    private val navigator: Navigator by lazy { SupportAppNavigator(this,
        R.id.content
    ) }
    private val appModule by lazy { ApplicationLoader.instance.appModule }

    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainPresenter>
    @InjectPresenter
    lateinit var presenter: MainPresenter
    @ProvidePresenter
    fun provide(): MainPresenter = presenterLazy.get()

    init {
        DaggerAppComponent
            .builder()
            .appModule(appModule)
            .build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadData()
    }

    override fun onDataLoaded(success: Boolean) {
        if (success.not()) wait.setText(R.string.error)
        wait.visibility(success.not())
    }

    override fun onPause() {
        super.onPause()
        appModule.navigationHolder.removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        appModule.navigationHolder.setNavigator(navigator)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}