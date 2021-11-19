package com.kurilov.worldscillstrainingtaskone.ui.singIn.singInFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.worldscillstrainingtaskone.data.classes.LoginBody
import com.kurilov.worldscillstrainingtaskone.data.classes.MyResult
import com.kurilov.worldscillstrainingtaskone.data.classes.RegisterBody
import com.kurilov.worldscillstrainingtaskone.data.classes.SUAIChatAnswer
import com.kurilov.worldscillstrainingtaskone.data.repositories.ApiRepo
import kotlinx.coroutines.launch

class SingInViewModel : ViewModel() {

    private val apiRepo = ApiRepo()

    private val _serverAnswer = MutableLiveData<MyResult<SUAIChatAnswer>>()
    val serverAnswer : LiveData<MyResult<SUAIChatAnswer>>
        get() = _serverAnswer

    fun loginUser(login : String, password : String) {
        viewModelScope.launch {
            try {
                _serverAnswer.value = MyResult.success(data = apiRepo.loginUser(
                    LoginBody(
                        email = login,
                        password = password
                    )
                ))
            } catch (exception: Exception) {
                _serverAnswer.value = MyResult.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }

}