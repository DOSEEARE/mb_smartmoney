package com.molbulak.smartmoney.service.network.response.question

import com.molbulak.smartmoney.service.network.response.CrmError

data class QuestionResponse(
    val code: Int,
    val result: List<Question>,
    val error : CrmError
)

data class Question(
    val id: String,
    val name: String
)