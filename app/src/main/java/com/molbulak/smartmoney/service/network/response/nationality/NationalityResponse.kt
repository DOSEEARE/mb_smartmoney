package com.molbulak.smartmoney.service.network.response.nationality

import com.molbulak.smartmoney.service.network.response.CrmError

data class NationalityResponse(
    val code: Int,
    val result: List<Nation>?,
    val error : CrmError?
)
data class Nation(
    val id: String,
    val name: String
)