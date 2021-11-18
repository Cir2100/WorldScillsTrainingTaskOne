package com.kurilov.worldscillstrainingtaskone.data.repositories

import com.kurilov.worldscillstrainingtaskone.data.api.RetrofitBuilder
import com.kurilov.worldscillstrainingtaskone.data.classes.LoginBody
import com.kurilov.worldscillstrainingtaskone.data.classes.RegisterBody

class ApiRepo {

    private val apiService = RetrofitBuilder.apiService

    suspend fun getCountries() = apiService.getCountries()

    suspend fun loginUser(loginBody: LoginBody) = apiService.loginUser(loginBody)

    suspend fun registerUser(registerBody: RegisterBody) = apiService.registerUser(registerBody)
}