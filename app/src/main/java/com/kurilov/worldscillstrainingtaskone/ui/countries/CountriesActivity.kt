package com.kurilov.worldscillstrainingtaskone.ui.countries

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kurilov.worldscillstrainingtaskone.databinding.ActivityCountryBinding
import com.kurilov.worldscillstrainingtaskone.data.classes.Status

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding
    private lateinit var viewModel: CountriesViewModel
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(CountriesViewModel::class.java)
    }

    private fun setupUI() {
        binding.countryRecyclerview.layoutManager = LinearLayoutManager(this)

        adapter = CountriesAdapter()
        binding.countryRecyclerview.adapter = adapter

    }

    private fun setupObservers() {
        viewModel.getCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.countryRecyclerview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { countries -> adapter.updateItems(countries) }
                    }
                    Status.ERROR -> {
                        binding.countryRecyclerview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.countryRecyclerview.visibility = View.GONE
                    }
                }
            }
        })
    }
}