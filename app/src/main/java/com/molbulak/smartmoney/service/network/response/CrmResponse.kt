package com.molbulak.smartmoney.service.network.response

class CrmResponse<T : Any>(
    val code: Int,
    val error: CrmError,
    val result: T
)

class CrmError(
    code: Int,
    message: String
)