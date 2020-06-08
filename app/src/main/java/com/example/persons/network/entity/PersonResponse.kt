package com.example.persons.network.entity

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("f_name") val firstName: String?,
    @SerializedName("l_name" ) val lastName: String?,
    @SerializedName("birthday") val birthDay: String?,
    @SerializedName("avatr_url") val image: String?,
    @SerializedName("specialty") val specList: List<SpecialityResponse>?
)

