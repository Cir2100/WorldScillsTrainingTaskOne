package com.kurilov.worldscillstrainingtaskone.ui.countries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.databinding.ActivityCountryBinding

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_countries)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_countries)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}