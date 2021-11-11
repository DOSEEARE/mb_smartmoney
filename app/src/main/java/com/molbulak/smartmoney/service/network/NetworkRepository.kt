package com.molbulak.smartmoney.service.network

import android.content.Context

class NetworkRepository(private val context: Context) {
/*    fun auth(model: AuthBody) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitClient(context).apiService(false).auth(model)
            when {
                response.isSuccessful -> {
                    emit(Resource.success(response.body()))
                }
                else -> {
                    response.raw().request.url
                    badRequest(response)
                    emit(Resource.error("Неверный логин или пароль", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.network("Проблемы с подключением интернета", null))
        }
    }*/

}





