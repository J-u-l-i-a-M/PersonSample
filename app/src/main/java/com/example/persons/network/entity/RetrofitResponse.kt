package com.example.persons.network.entity

import com.example.persons.network.entity.PersonResponse
import com.google.gson.annotations.SerializedName

data class RetrofitResponse(
    @SerializedName("response") val persons: List<PersonResponse>?
)