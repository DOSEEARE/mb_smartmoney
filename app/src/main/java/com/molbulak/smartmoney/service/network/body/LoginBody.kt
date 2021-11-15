package com.molbulak.smartmoney.service.network.body

class LoginBody(
    val appid: String,
    val login: String,
    val password: String,
    val system: Int,
    val uid: String,
)