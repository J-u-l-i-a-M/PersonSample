package com.example.persons.views

import com.example.persons.database.entity.Person
import com.example.persons.database.entity.Speciality
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PersonFullView : BaseMoxyView {
    fun onDataLoaded(person: Person, specs: List<Speciality>)
}