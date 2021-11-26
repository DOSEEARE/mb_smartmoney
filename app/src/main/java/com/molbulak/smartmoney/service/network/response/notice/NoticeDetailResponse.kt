package com.molbulak.smartmoney.service.network.response.notice

import com.molbulak.smartmoney.service.network.response.CrmError

data class NoticeDetailResponse(
    val code: Int,
    val result: NoticeDetail?,
    val error : CrmError?
)

data class NoticeDetail(
    val date: String,
    val description: String,
    val id: String,
    val text: String,
    val title: String
)