package com.kurilov.worldscillstrainingtaskone.ui.singIn.singUpFragment

import androidx.lifecycle.*
import com.kurilov.worldscillstrainingtaskone.data.classes.MyResult
import com.kurilov.worldscillstrainingtaskone.data.classes.RegisterBody
import com.kurilov.worldscillstrainingtaskone.data.classes.SUAIChatAnswer
import com.kurilov.worldscillstrainingtaskone.data.repositories.ApiRepo
import kotlinx.coroutines.launch

class SingUpViewModel : ViewModel() {

    private val apiRepo = ApiRepo()

    private val _serverAnswer = MutableLiveData<MyResult<SUAIChatAnswer>>()
    val serverAnswer : LiveData<MyResult<SUAIChatAnswer>>
        get() = _serverAnswer

    fun registerUser(login : String, email : String, password : String) {
        viewModelScope.launch {
            //MyResult.loading(data = null)
            try {
                _serverAnswer.value = MyResult.success(data = apiRepo.registerUser(
                    RegisterBody(
                        firstName = login,
                        lastName = login ,
                        email = email,
                        password = password
                )
                ))
            } catch (exception: Exception) {
                _serverAnswer.value = MyResult.error(data = null, message = exception.message ?: "Error Occurred!")
            }

        }
    }
}