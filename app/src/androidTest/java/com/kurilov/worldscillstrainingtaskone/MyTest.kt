package com.kurilov.worldscillstrainingtaskone

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kurilov.worldscillstrainingtaskone.data.api.ApiService
import com.kurilov.worldscillstrainingtaskone.data.api.RetrofitBuilder
import com.kurilov.worldscillstrainingtaskone.data.classes.LoginBody
import com.kurilov.worldscillstrainingtaskone.data.classes.SUAIChatAnswer
import com.kurilov.worldscillstrainingtaskone.data.classes.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.rules.ExpectedException

import org.junit.Rule




@RunWith(AndroidJUnit4::class)
class MyTest {

    private val apiService = RetrofitBuilder.apiService

    @Test
    fun userLoginSuccess() = runBlocking {
        val loginBody = LoginBody("a@a.ru", "a31122001")
        val ans = apiService.loginUser(loginBody)
        assertEquals(ans.state, "ok")
    }

    @Test(expected = retrofit2.HttpException::class)
    fun userLoginUnSuccess(): Unit = runBlocking {
        val loginBody = LoginBody("a@aa.ru", "a31122001")
        apiService.loginUser(loginBody)
    }

    @Test(expected = retrofit2.HttpException::class)
    fun userLoginUnSuccess2(): Unit = runBlocking {
        val loginBody = LoginBody("a@a.ru", "")
        apiService.loginUser(loginBody)
    }
}