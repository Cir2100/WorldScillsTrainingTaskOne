package com.kurilov.worldscillstrainingtaskone.data.api

import com.kurilov.worldscillstrainingtaskone.data.classes.Country
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    suspend fun getCountries(): List<Country>


}