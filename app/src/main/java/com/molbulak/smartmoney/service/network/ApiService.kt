package com.molbulak.smartmoney.service.network

import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.service.network.response.CrmResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import ru.smartro.worknote.service.network.response.auth.LoginResponse

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginBody: LoginBody)
            : Response<CrmResponse<LoginResponse>>

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