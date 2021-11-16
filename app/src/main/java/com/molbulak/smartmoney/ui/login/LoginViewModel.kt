package com.molbulak.smartmoney.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.body.CheckPhoneBody
import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.service.network.response.check_phone.CheckPhoneResponse
import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.gender.GenderResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse
import com.molbulak.smartmoney.service.network.response.nationality.NationalityResponse
import com.molbulak.smartmoney.service.network.response.question.QuestionResponse

class LoginViewModel(application: Application) : BaseViewModel(application) {

    fun login(authModel: LoginBody): LiveData<Resource<LoginResponse>> {
        return network.login(authModel)
    }

    fun availableCountry(): LiveData<Resource<CountryResponse>> {
        return network.availableCountry()
    }

    fun checkPhone(body: CheckPhoneBody): LiveData<Resource<CheckPhoneResponse>> {
        return network.checkPhone(body)
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

}