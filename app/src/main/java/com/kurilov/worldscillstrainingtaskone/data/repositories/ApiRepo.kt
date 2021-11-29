package com.kurilov.worldscillstrainingtaskone.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kurilov.worldscillstrainingtaskone.data.api.RetrofitBuilder
import com.kurilov.worldscillstrainingtaskone.data.classes.Country
import com.kurilov.worldscillstrainingtaskone.data.classes.LoginBody
import com.kurilov.worldscillstrainingtaskone.data.classes.MyResult
import com.kurilov.worldscillstrainingtaskone.data.classes.RegisterBody

object ApiRepo {

    private val apiService = RetrofitBuilder.apiService

    private val _countries = MutableLiveData<MyResult<List<Country>>>()
    val countries : LiveData<MyResult<List<Country>>>
        get() = _countries

    suspend fun getCountries() {
        _countries.postValue(MyResult.loading(data = null))
        try {
            _countries.postValue(MyResult.success(data = apiService.getCountries()))
        } catch (exception: Exception) {
            _countries.postValue(MyResult.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    suspend fun loginUser(loginBody: LoginBody) = apiService.loginUser(loginBody)

    suspend fun registerUser(registerBody: RegisterBody) = apiService.registerUser(registerBody)
}