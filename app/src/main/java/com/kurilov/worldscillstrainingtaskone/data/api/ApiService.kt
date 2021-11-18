package com.kurilov.worldscillstrainingtaskone.data.api

import com.kurilov.worldscillstrainingtaskone.data.classes.Country
import com.kurilov.worldscillstrainingtaskone.data.classes.LoginBody
import com.kurilov.worldscillstrainingtaskone.data.classes.RegisterBody
import com.kurilov.worldscillstrainingtaskone.data.classes.SUAIChatAnswer
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("https://restcountries.com/v3.1/all")
    suspend fun getCountries(): List<Country>

    @POST("auth/login")
    suspend fun loginUser(@Body loginBody : LoginBody): SUAIChatAnswer

    @POST("auth/register")
    suspend fun registerUser(@Body registerBody: RegisterBody): SUAIChatAnswer

}