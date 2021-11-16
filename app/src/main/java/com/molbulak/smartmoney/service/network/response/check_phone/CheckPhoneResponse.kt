package com.molbulak.smartmoney.service.network.response.check_phone

import com.molbulak.smartmoney.service.network.response.CrmError

data class CheckPhoneResponse(
    val code: Int,
    val result: CheckPhoneResult?,
    val error: CrmError?,
)

data class CheckPhoneResult(
    val id: Int,
)