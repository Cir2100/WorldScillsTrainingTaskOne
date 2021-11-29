package com.kurilov.worldscillstrainingtaskone.ui.countries.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.worldscillstrainingtaskone.data.repositories.ApiRepo
import kotlinx.coroutines.launch

class CountriesListViewModel : ViewModel() {

    private val apiRepo = ApiRepo
    val countries = apiRepo.countries

    init {
        getCountries()
    }

    private fun getCountries(){
        viewModelScope.launch {
            apiRepo.getCountries()
        }
    }
}