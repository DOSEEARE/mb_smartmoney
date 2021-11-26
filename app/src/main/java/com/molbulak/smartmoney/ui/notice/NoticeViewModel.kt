package com.molbulak.smartmoney.ui.notice

import android.app.Application
import androidx.lifecycle.LiveData
import com.molbulak.smartmoney.base.BaseViewModel
import com.molbulak.smartmoney.service.network.Resource
import com.molbulak.smartmoney.service.network.response.notice.NoticeDetailResponse
import com.molbulak.smartmoney.service.network.response.notice.NoticeListResponse

class NoticeViewModel(application: Application) : BaseViewModel(application) {

    val listNoticeList: LiveData<Resource<NoticeListResponse>> = network.listNotice()
    val noticeDetail =
        fun(id: String): LiveData<Resource<NoticeDetailResponse>> = network.noticeDetail(id)
}