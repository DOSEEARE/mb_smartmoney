package com.molbulak.smartmoney.service.network

import android.util.Log
import com.molbulak.smartmoney.service.preference.AppPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("token", AppPreferences.token)
            .addHeader("api-key", "Cq3Kry")
            .addHeader("login", AppPreferences.login)
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private var httpLoggingInterceptor = run {
        val httpLoggingInterceptor1 =
            HttpLoggingInterceptor { message -> Log.d("okhttp", message) }
        httpLoggingInterceptor1.apply {
            httpLoggingInterceptor1.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(240, TimeUnit.SECONDS)
            .readTimeout(240, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .build()

    private fun retrofit(baseUrl: String) =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun apiService() =
        retrofit("https://crm-api-dev.molbulak.ru/api/app/").create(ApiService::class.java)
}
