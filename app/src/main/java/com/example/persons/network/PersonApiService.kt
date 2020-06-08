package com.example.persons.network

import com.example.persons.network.entity.RetrofitResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface PersonApiService {
    @GET("/65gb/static/raw/master/testTask.json")
    fun getPerson(): Observable<RetrofitResponse>
}