package com.molbulak.smartmoney.service.network.response.faq

import com.molbulak.smartmoney.service.network.response.CrmError

data class FaqResponse(
    val code: Int,
    val result: List<Faq>?,
    val error: CrmError?
)

data class Faq(
    val id: String,
    val name: String,
    val text: String
)