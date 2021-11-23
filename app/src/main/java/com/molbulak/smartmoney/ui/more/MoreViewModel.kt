package com.molbulak.smartmoney.ui.more

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.body.*
import com.molbulak.smartmoney.service.network.response.AuthResponse
import com.molbulak.smartmoney.service.network.response.check_code.CheckCodeResponse
import com.molbulak.smartmoney.service.network.response.check_phone.CheckPhoneResponse
import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.gender.GenderResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse
import com.molbulak.smartmoney.service.network.response.nationality.NationalityResponse
import com.molbulak.smartmoney.service.network.response.question.QuestionResponse
import com.molbulak.smartmoney.service.network.response.restore.RestoreResponse

class MoreViewModel(application: Application) : BaseViewModel(application) {

    fun login(authModel: LoginBody): LiveData<Resource<LoginResponse>> {
        return network.login(authModel)
    }

    fun availableCountry(): LiveData<Resource<CountryResponse>> {
        return network.availableCountry()
    }

    fun checkPhone(body: CheckPhoneBody): LiveData<Resource<CheckPhoneResponse>> {
        return network.checkPhone(body)
    }

    fun checkCode(body: CheckCodeBody): LiveData<Resource<CheckCodeResponse>> {
        return network.checkCode(body)
    }

    fun gender(): LiveData<Resource<GenderResponse>> {
        return network.gender()
    }

    fun nationality(): LiveData<Resource<NationalityResponse>> {
        return network.nationality()
    }

    fun question(): LiveData<Resource<QuestionResponse>> {
        return network.question()
    }

    fun auth(body: AuthBody): LiveData<Resource<AuthResponse>> {
        return network.auth(body)
    }

    fun restore(body: RestoreBody): LiveData<Resource<RestoreResponse>> {
        return network.restore(body)
    }
}