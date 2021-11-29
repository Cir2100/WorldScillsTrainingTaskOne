package com.kurilov.worldscillstrainingtaskone.ui.countries.diagramm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.data.classes.Status
import com.kurilov.worldscillstrainingtaskone.databinding.FragmentDiagrammBinding
import com.kurilov.worldscillstrainingtaskone.databinding.FragmentListBinding

class CountriesDiagrammFragment : Fragment(){

    private var _binding: FragmentDiagrammBinding? = null
    private lateinit var viewModel: CountriesDiagrammViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDiagrammBinding.inflate(inflater, container, false)
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
            .get(CountriesDiagrammViewModel::class.java)
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_CountriesDiagrammFragment_to_CountriesListFragment)
        }

        binding.diagramTopButton.setOnCheckedChangeListener { _, isChecked ->
            binding.diagramRandButton.isChecked = !isChecked
            if (isChecked)
                viewModel.getTopCountries()
        }

        binding.diagramRandButton.setOnCheckedChangeListener { _, isChecked ->
            binding.diagramTopButton.isChecked = !isChecked
            if (isChecked)
                viewModel.getRandCountries()
        }

        binding.diagram.description.isEnabled = false
        binding.diagram.axisRight.isEnabled = false
        binding.diagram.axisLeft.setDrawLabels(false)
        binding.diagram.axisLeft.setDrawAxisLine(false)
        binding.diagram.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

    private fun setupObservers() {
        viewModel.entries.observe(viewLifecycleOwner) {
            it?.let { data->
                binding.diagram.clear()
                binding.diagram.data =  BarData(BarDataSet(data, "Countries"))

            }
        }
        viewModel.countriesList.observe(viewLifecycleOwner) {
            it?.let {
                binding.countryList.text = "1-${it[0]}\n2-${it[1]}\n3-${it[2]}\n4-${it[3]}\n5-${it[4]}\n"
            }
        }
    }
}