package com.kurilov.worldscillstrainingtaskone.ui.singIn.singInFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.data.classes.Status
import com.kurilov.worldscillstrainingtaskone.databinding.FragmentSingInBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SingInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private lateinit var viewModel: SingInViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingInBinding.inflate(inflater, container, false)
        setupViewModel()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(SingInViewModel::class.java)
    }

    private fun setupUI() {

        binding.toSingUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_CountriesListFragment_to_CountriesDiagrammFragment)
        }

        binding.singInButton.setOnClickListener {
            if (isValidate()) {
                viewModel.loginUser(
                    login = binding.loginInput.text.toString().trim(),
                    password = binding.passwordInput.text.toString().trim()
                )
            }
        }

        viewModel.serverAnswer.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        findNavController().navigate(R.id.action_SingInFragment_to_CountriesActivity)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun isValidate(): Boolean =
        validateLogin() && validatePassword()

    private fun validateLogin() : Boolean {
        if (binding.loginInput.text.toString().trim().isEmpty()) {
            binding.loginInputLayout.error = "Логин не должен быть пустым"
            return false
        } else {
            binding.loginInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword() : Boolean {
        if (binding.passwordInput.text.toString().trim().isEmpty()) {
            binding.passwordInputLayout.error = "Пароль не должен быть пустым"
            return false
        } else {
            binding.passwordInputLayout.isErrorEnabled = false
        }
        return true
    }
}