package com.molbulak.smartmoney.service.network.response.login

import com.molbulak.smartmoney.service.network.response.CrmError

class LoginResponse(
    val code: Int,
    val result: LoginResult?,
    val error: CrmError?
)

class LoginResult(
    val login: String,
    val token: String,
)



