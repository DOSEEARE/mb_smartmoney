package com.molbulak.smartmoney.service.network

import com.molbulak.smartmoney.service.network.response.AuthResponse
import com.molbulak.smartmoney.service.network.response.check_code.CheckCodeResponse
import com.molbulak.smartmoney.service.network.response.check_phone.CheckPhoneResponse
import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.gender.GenderResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse
import com.molbulak.smartmoney.service.network.response.nationality.NationalityResponse
import com.molbulak.smartmoney.service.network.response.question.QuestionResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login/")
    suspend fun login(@Body loginBody: MultipartBody)
            : Response<LoginResponse>

    @GET("listAvailableCountry/")
    suspend fun availableCountry()
            : Response<CountryResponse>

    @POST("checkPhone/")
    suspend fun checkPhone(@Body checkPhoneBody: MultipartBody)
            : Response<CheckPhoneResponse>

    @POST("checkCode/")
    suspend fun checkCode(@Body checkPhoneBody: MultipartBody)
            : Response<CheckCodeResponse>

    @POST("listGender/")
    suspend fun gender(@Body genderBody: MultipartBody)
            : Response<GenderResponse>

    @POST("listNationality/")
    suspend fun nationality(@Body genderBody: MultipartBody)
            : Response<NationalityResponse>

    @POST("listSecretQuestion/")
    suspend fun question(@Body genderBody: MultipartBody)
            : Response<QuestionResponse>

    @POST("registration/")
    suspend fun auth(@Body genderBody: MultipartBody)
            : Response<AuthResponse>


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