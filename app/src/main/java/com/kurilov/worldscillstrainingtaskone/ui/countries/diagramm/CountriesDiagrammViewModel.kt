package com.kurilov.worldscillstrainingtaskone.ui.countries.diagramm

import android.util.Log
import androidx.lifecycle.*
import com.github.mikephil.charting.data.BarEntry
import com.kurilov.worldscillstrainingtaskone.data.classes.Country
import com.kurilov.worldscillstrainingtaskone.data.repositories.ApiRepo
import kotlinx.coroutines.launch


class CountriesDiagrammViewModel : ViewModel() {

    private val apiRepo = ApiRepo
    private val countries =  apiRepo.countries
    private val _entries = MutableLiveData<List<BarEntry>>()
    val entries : LiveData<List<BarEntry>>
        get() = _entries

    private val _countriesList = MutableLiveData<List<String>>()
    val countriesList : LiveData<List<String>>
        get() = _countriesList

    init {
        getTopCountries()
    }

    fun getTopCountries() {
        viewModelScope.launch {
            var countriesSortedList = listOf<Country>()
            countries.value?.let {
                it.data?.let { data ->
                    countriesSortedList = data.sortedByDescending { country -> country.population }
                }
            }
            if (countriesSortedList.size > 5) {
                val entry = mutableListOf<BarEntry>()
                val names = mutableListOf<String>()
                for (i in 0..5) {
                    entry.add(BarEntry(i.toFloat(), countriesSortedList[i].population.toFloat()))
                    names.add(countriesSortedList[i].name.common)
                }
                _entries.value = entry
                _countriesList.value = names

            }
        }
    }

    fun getRandCountries() {
        viewModelScope.launch {
            countries.value?.let {
                it.data?.let { data ->
                    val entry = mutableListOf<BarEntry>()
                    val names = mutableListOf<String>()
                    for (i in 0..5) {
                        val index = (data.indices).random()
                        entry.add(BarEntry(i.toFloat(), data[index].population.toFloat()))
                        names.add(data[index].name.common)
                    }
                    _entries.value = entry
                    _countriesList.value = names
                }
            }
        }
    }
}