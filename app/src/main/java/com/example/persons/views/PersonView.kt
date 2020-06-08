package com.example.persons.views

import com.example.persons.database.entity.Person
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PersonView : BaseMoxyView {
    fun onDataLoaded(items: List<Person>)
}