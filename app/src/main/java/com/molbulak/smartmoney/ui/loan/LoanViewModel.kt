package com.molbulak.smartmoney.ui.loan

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.response.news.NewsResponse

class LoanViewModel(application: Application) : BaseViewModel(application) {
    fun listNews(): LiveData<Resource<NewsResponse>> {
        return network.listNews()
    }
}