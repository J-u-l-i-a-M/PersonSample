package com.example.persons.network.entity

import com.google.gson.annotations.SerializedName

data class SpecialityResponse (
    @SerializedName("specialty_id") val specId: Int,
    @SerializedName("name" ) val specName: String?
)