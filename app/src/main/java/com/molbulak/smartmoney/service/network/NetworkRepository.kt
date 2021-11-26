package com.molbulak.smartmoney.service.network

import android.content.Context
import androidx.lifecycle.liveData
import com.molbulak.smartmoney.service.network.body.*
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
            val response = RetrofitClient.apiService().login(formData)
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

    fun checkPhone(body: CheckPhoneBody) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("phone", body.phone)
                build()
            }
            val response = RetrofitClient.apiService().checkPhone(formData)
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
        } catch (exception: Exception) {
            emit(Resource.network(null, "Проблемы с подключением", 0))
        }
    }

    fun checkCode(body: CheckCodeBody) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("id", body.id.toString())
                addFormDataPart("code", body.code)
                build()
            }
            val response = RetrofitClient.apiService().checkCode(formData)
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
        } catch (exception: Exception) {
            emit(Resource.network(null, "Проблемы с подключением", 0))
        }
    }

    fun gender() = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("id", "0")
                build()
            }
            val response = RetrofitClient.apiService().gender(formData)
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

    fun nationality() = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("id", "0")
                build()
            }
            val response = RetrofitClient.apiService().nationality(formData)
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

    fun question() = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("id", "0")
                build()
            }
            val response = RetrofitClient.apiService().question(formData)
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

    fun auth(body: AuthBody) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("first_name", body.first_name)
                addFormDataPart("first_phone", body.first_phone)
                addFormDataPart("gender", body.gender)
                addFormDataPart("last_name", body.last_name)
                addFormDataPart("nationality", body.nationality)
                addFormDataPart("question", body.question)
                addFormDataPart("response", body.response)
                addFormDataPart("second_name", body.second_name)
                addFormDataPart("second_phone", body.second_phone)
                addFormDataPart("sms_code", body.sms_code)
                addFormDataPart("system", body.system)
                addFormDataPart("u_date", body.u_date)
                build()
            }
            val response = RetrofitClient.apiService().auth(formData)
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

    fun restore(body: RestoreBody) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("phone", body.phone)
                addFormDataPart("response", body.response)
                build()
            }
            val response = RetrofitClient.apiService().restore(formData)
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

    fun noticeDetail(id: String) = liveData(Dispatchers.IO) {
        try {
            val formData = MultipartBody.Builder().run {
                setType(MultipartBody.FORM)
                addFormDataPart("id", id)
                build()
            }
            val response = RetrofitClient.apiService().noticeDetail(formData)
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

    fun listNews() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitClient.apiService().listNews()
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

    fun listNotice() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitClient.apiService().listNotice()
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
            val response = RetrofitClient.apiService().availableCountry()
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





