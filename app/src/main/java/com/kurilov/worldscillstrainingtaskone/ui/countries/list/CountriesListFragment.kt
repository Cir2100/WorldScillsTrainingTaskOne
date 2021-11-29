package com.kurilov.worldscillstrainingtaskone.ui.countries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.data.classes.Status
import com.kurilov.worldscillstrainingtaskone.databinding.FragmentListBinding

class CountriesListFragment : Fragment(){

    private var _binding: FragmentListBinding? = null
    private lateinit var viewModel: CountriesListViewModel
    private val binding get() = _binding!!
    private lateinit var adapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        setupViewModel()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(CountriesListViewModel::class.java)
    }

    private fun setupUI() {
        binding.countryRecyclerview.layoutManager = LinearLayoutManager(activity)

        adapter = CountriesAdapter()
        binding.countryRecyclerview.adapter = adapter

        binding.toStatsButton.setOnClickListener {
            findNavController().navigate(R.id.action_CountriesListFragment_to_CountriesDiagrammFragment)
        }
    }

    private fun setupObservers() {
        viewModel.countries.observe(viewLifecycleOwner) {
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
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.countryRecyclerview.visibility = View.GONE
                    }
                }
            }
        }
    }
}