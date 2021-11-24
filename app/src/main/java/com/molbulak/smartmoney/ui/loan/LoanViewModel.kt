package com.molbulak.smartmoney.ui.loan

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.response.news.NewsResponse

class LoanViewModel(application: Application) : BaseViewModel(application) {
    val listNews = listNews()

    fun listNews(): LiveData<Resource<NewsResponse>> {
        return network.listNews()
    }
}