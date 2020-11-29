package com.agladkov.data.remote

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("./countries")
    fun getCountries(): Single<String>
}