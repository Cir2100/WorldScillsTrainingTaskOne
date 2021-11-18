package com.kurilov.worldscillstrainingtaskone.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kurilov.worldscillstrainingtaskone.data.classes.MyResult
import com.kurilov.worldscillstrainingtaskone.data.repositories.ApiRepo
import kotlinx.coroutines.Dispatchers

class CountriesViewModel : ViewModel() {

    private val apiRepo = ApiRepo()

    fun getCountries() = liveData(Dispatchers.IO) {
        emit(MyResult.loading(data = null))
        try {
            emit(MyResult.success(data = apiRepo.getCountries()))
        } catch (exception: Exception) {
            emit(MyResult.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}