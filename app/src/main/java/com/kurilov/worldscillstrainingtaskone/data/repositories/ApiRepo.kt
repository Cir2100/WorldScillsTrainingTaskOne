package com.kurilov.worldscillstrainingtaskone.data.repositories

import com.kurilov.worldscillstrainingtaskone.data.api.RetrofitBuilder

class ApiRepo {

    private val apiService = RetrofitBuilder.apiService

    suspend fun getCountries() = apiService.getCountries()
}