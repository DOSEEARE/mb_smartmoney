package com.molbulak.smartmoney.service.network.response

data class AuthResponse(
    val code: Int,
    val result: AuthResult?,
    val error: CrmError?,
)

data class AuthResult(
    val code: Int,
    val message: String,
)