package com.molbulak.smartmoney.ui.notification

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.response.notice.NoticeResponse

class NoticeViewModel(application: Application) : BaseViewModel(application) {

    val listNotice: LiveData<Resource<NoticeResponse>> = network.listNotice()

}