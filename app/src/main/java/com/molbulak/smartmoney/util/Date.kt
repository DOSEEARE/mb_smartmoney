package com.molbulak.smartmoney.util

class Date(val day: Int, val month: Int, val year: Int) {
    fun getDate() = "${checkDigit(day)}.${checkDigit(month)}.$year"

    private fun checkDigit(number: Int): String {
        return if (number <= 9) "0$number" else number.toString()
    }
}