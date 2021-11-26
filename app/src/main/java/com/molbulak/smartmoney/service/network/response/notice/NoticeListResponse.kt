package com.molbulak.smartmoney.service.network.response.notice

import com.molbulak.smartmoney.service.network.response.CrmError

data class NoticeListResponse(
    val code: Int,
    val result: List<Notice>?,
    val error: CrmError?
)

data class Notice(
    val date: String,
    val description: String,
    val detail: Boolean,
    val id: String,
    val review: Boolean,
    val title: String
)