package com.molbulak.smartmoney.service.network.response.check_code

import com.molbulak.smartmoney.service.network.response.CrmError

data class CheckCodeResponse(
    val code: Int,
    val result: Result?,
    val error: CrmError?,
)