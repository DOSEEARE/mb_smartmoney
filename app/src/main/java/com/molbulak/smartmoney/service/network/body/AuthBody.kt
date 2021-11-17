package com.molbulak.smartmoney.service.network.body

data class AuthBody(
    val first_name: String,
    val first_phone: String,
    val gender: String,
    val last_name: String,
    val nationality: String,
    val question: String,
    val response: String,
    val second_name: String,
    val second_phone: String,
    val sms_code: String,
    val system: String,
    val u_date: String,
)