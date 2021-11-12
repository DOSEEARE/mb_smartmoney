package com.molbulak.smartmoney.service.network

import android.content.Context
import androidx.lifecycle.liveData
import com.molbulak.smartmoney.service.network.body.LoginBody
import kotlinx.coroutines.Dispatchers

class NetworkRepository(private val context: Context) {
    fun auth(body: LoginBody) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitClient().apiService().login(body)
            when {
                response.isSuccessful -> {
                    val crmCode = response.body()?.code
                    when {
                         crmCode in 200..300 -> {
                             emit(Resource.success(response.body()))
                         }
                    }
                }
                else -> {
                    response.raw().request.url
                    emit(Resource.error("Неверный логин или пароль", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.network("Проблемы с подключением интернета", null))
        }
    }

}





