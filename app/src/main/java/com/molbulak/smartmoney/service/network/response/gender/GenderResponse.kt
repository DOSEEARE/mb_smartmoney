package com.molbulak.smartmoney.service.network.response.gender

import com.molbulak.smartmoney.service.network.response.CrmError

data class GenderResponse(
    val code: Int,
    val result: List<Gender>?,
    val error : CrmError?
)

data class Gender(
    val id: String,
    val name: String
)