package com.molbulak.smartmoney.service.network.response.restore

import com.molbulak.smartmoney.service.network.response.CrmError

data class RestoreResponse(
    val code: Int,
    val result: RestoreResult?,
    val error : CrmError?
)

data class RestoreResult(
    val code: Int,
    val message: String
)