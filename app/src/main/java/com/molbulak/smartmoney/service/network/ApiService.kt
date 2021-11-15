package com.molbulak.smartmoney.service.network

import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login/")
    suspend fun login(@Body loginBody: MultipartBody)
            : Response<LoginResponse>

    @POST("listAvailableCountry/")
    suspend fun availableCountry()
            : Response<CountryResponse>

/*
    @POST("login")
    suspend fun login(@Query("token") token: String, @Body loginBody: LoginBody)
            : Response<CrmResponse<LoginResponse>>

    @GET("vehicle")
    suspend fun getVehicle(@Query("o") organisationId: Int): Response<VehicleResponse>

    @GET("breakdown_type?page=all")
    suspend fun getBreakDownTypes(): Response<BreakDownResponse>

    @GET("failure_reason?page=all")
    suspend fun getFailReason(): Response<FailureReasonResponse>*/
}