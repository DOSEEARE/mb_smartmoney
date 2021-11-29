package com.molbulak.smartmoney.service.network

import com.molbulak.smartmoney.service.network.response.AuthResponse
import com.molbulak.smartmoney.service.network.response.check_code.CheckCodeResponse
import com.molbulak.smartmoney.service.network.response.check_phone.CheckPhoneResponse
import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.faq.FaqResponse
import com.molbulak.smartmoney.service.network.response.gender.GenderResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse
import com.molbulak.smartmoney.service.network.response.nationality.NationalityResponse
import com.molbulak.smartmoney.service.network.response.news.NewsResponse
import com.molbulak.smartmoney.service.network.response.notice.NoticeDetailResponse
import com.molbulak.smartmoney.service.network.response.notice.NoticeListResponse
import com.molbulak.smartmoney.service.network.response.question.QuestionResponse
import com.molbulak.smartmoney.service.network.response.restore.RestoreResponse
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

    @POST("recoveryAccess/")
    suspend fun restore(@Body genderBody: MultipartBody)
            : Response<RestoreResponse>

    @POST("listNews/")
    suspend fun listNews()
            : Response<NewsResponse>

    @POST("listNotice/")
    suspend fun listNotice()
            : Response<NoticeListResponse>

    @POST("getNotice/")
    suspend fun noticeDetail(@Body noticeDetailBody : MultipartBody)
            : Response<NoticeDetailResponse>

    @POST("listFaq/")
    suspend fun listFaq()
            : Response<FaqResponse>
}