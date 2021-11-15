package com.molbulak.smartmoney.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.service.network.response.country.CountryResponse
import com.molbulak.smartmoney.service.network.response.login.LoginResponse

class LoginViewModel(application: Application) : BaseViewModel(application) {

    fun login(authModel: LoginBody): LiveData<Resource<LoginResponse>> {
        return network.login(authModel)
    }

    fun availableCountry(): LiveData<Resource<CountryResponse>> {
        return network.availableCountry()
    }

}