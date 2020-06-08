package com.example.persons.views

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : BaseMoxyView {
    fun onDataLoaded(success: Boolean)
}