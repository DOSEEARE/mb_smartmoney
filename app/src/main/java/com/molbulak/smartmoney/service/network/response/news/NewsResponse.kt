package com.molbulak.smartmoney.service.network.response.news

import com.molbulak.smartmoney.service.network.response.CrmError

data class NewsResponse(
    val code: Int,
    val result: List<News>?,
    val error : CrmError?
)

data class News(
    val description: String,
    val id: String,
    val name: String,
    val thumbnail: String
)