package com.molbulak.smartmoney.service.network

import android.content.Context
import androidx.lifecycle.liveData
import com.molbulak.smartmoney.service.AppPreferences
import com.molbulak.smartmoney.service.network.body.LoginBody
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class NetworkRepository(private val context: Context) {
    fun login(body: LoginBody) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("login", body.login)
                addFormDataPart("password", body.password)
                addFormDataPart("uid", body.uid)
                addFormDataPart("appid", body.appid)
                addFormDataPart("system", body.system.toString())
                build()
            }
            val response = RetrofitClient().apiService().login(formData)
            when {
                response.isSuccessful -> {
                    val crmCode = response.body()?.code
                    val response = response.body()
                    if (crmCode in 200..300) {
                        if (response?.result != null) {
                            emit(Resource.success(
                                data = response,
                                msg = "",
                                code = response.code))
                        }
                        if (response?.error != null) {
                            emit(Resource.error(
                                data = response,
                                msg = response.error.message,
                                code = response.error.code))
                        }
                    }
                }
                else -> {
                    response.raw().request.url
                    emit(Resource.error(null, response.message(), response.code()))
                }
            }
        } catch (e: Exception) {
            emit(Resource.network(null, "Проблемы с подключением интернета", 0))
        }
    }


    fun availableCountry() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitClient().apiService().availableCountry()
            when {
                response.isSuccessful -> {
                    val crmCode = response.body()?.code
                    val response = response.body()
                    if (crmCode in 200..300) {
                        if (response?.result != null) {
                            emit(Resource.success(
                                data = response,
                                msg = "",
                                code = response.code))
                        }
                        if (response?.error != null) {
                            emit(Resource.error(
                                data = response,
                                msg = response.error.message,
                                code = response.error.code))
                        }
                    }
                }
                else -> {
                    response.raw().request.url
                    emit(Resource.error(null, response.message(), response.code()))
                }
            }
        } catch (e: Exception) {
            emit(Resource.network(null, "Проблемы с подключением интернета", 0))
        }
    }

}





