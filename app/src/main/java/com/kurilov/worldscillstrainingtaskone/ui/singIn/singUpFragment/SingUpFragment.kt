package com.kurilov.worldscillstrainingtaskone.ui.singIn.singUpFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.data.classes.Status
import com.kurilov.worldscillstrainingtaskone.databinding.FragmentSingUpBinding
import com.kurilov.worldscillstrainingtaskone.ui.singIn.singInFragment.SingInViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SingUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null
    private lateinit var viewModel: SingUpViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
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
            .get(SingUpViewModel::class.java)
    }

    private fun setupUI() {

        binding.singUpButton.setOnClickListener {
            if (isValidate()) {
                viewModel.registerUser(
                    login = binding.loginInput.text.toString().trim(),
                    email = binding.emailInput.text.toString().trim(),
                    password = binding.passwordInput.text.toString().trim()
                )
            }
        }


        viewModel.serverAnswer.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        findNavController().navigate(R.id.action_SingUpFragment_to_CountriesActivity)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun isValidate(): Boolean =
        validateLogin() && validateEmail() && validatePassword() && validateConfirmPassword()

    private fun validateLogin() : Boolean {
        if (binding.loginInput.text.toString().trim().isEmpty()) {
            binding.loginInputLayout.error = "Логин не должен быть пустым"
            return false
        } else {
            binding.loginInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateEmail() : Boolean {

        fun checkFEmailFormat(string: String) : Boolean {
            var isFindA = false
            var isFindPoint = false
            string.forEach {
                if (it == '@') isFindA = true
                if (it == '.') isFindPoint = true
            }
            return isFindA && isFindPoint
        }

        fun checkString(string: String) : Boolean {
            string.forEach { if (!it.isDigit() && !it.isLowerCase()) return false }
            return true
        }

        fun checkFirstDomain(domain : String) : Boolean {
            domain.forEach{ if (!it.isLowerCase()) return false }
                return true
        }

        val post = binding.emailInput.text.toString().trim()
        val name = post.substringBefore("@")
        val domainFirst = post.trim().substringAfter("@").substringAfter(".")
        val domainSecond = post.trim().substringAfter("@").substringBefore(".")

        when {
            post.isEmpty() -> {
                binding.emailInputLayout.error = "E-mail не должен быть пустым"
                return false
            }
            !checkFEmailFormat(post) -> {
                binding.emailInputLayout.error = "E-mail не соответствует формату \"name@domenname.ru\""
                return false
            }
            !checkString(name) -> {
                binding.emailInputLayout.error = "Имя должно состоять только из строчных букв и цифр"
                return false
            }
            !checkString(domainSecond) -> {
                binding.emailInputLayout.error = "Домен второго уровня должен состоять только из строчных букв и цифр"
                return false
            }
            domainFirst.length > 3 -> {
                binding.emailInputLayout.error = "Длина домена первого уровня не более трех символов"
                return false
            }
            !checkFirstDomain(domainFirst) -> {
                binding.emailInputLayout.error = "Домен первого уровня должен состоять только из строчных букв"
                return false
            }
            else -> {
                binding.emailInputLayout.isErrorEnabled = false
            }
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

    private fun validateConfirmPassword() : Boolean {
        when {
            binding.confirmPasswordInput.text.toString().trim().isEmpty() -> {
                binding.confirmPasswordInputLayout.error = "Пароль не должен быть пустым"
                return false
            }
            binding.confirmPasswordInput.text.toString().trim() !=
                    binding.passwordInput.text.toString().trim() -> {
                binding.confirmPasswordInputLayout.error = "Пароли не совпадают"
                return false
            }
            else -> {
                binding.confirmPasswordInputLayout.isErrorEnabled = false
            }
        }
        return true
    }
}