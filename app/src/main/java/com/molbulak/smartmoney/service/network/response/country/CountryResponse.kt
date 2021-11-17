package com.molbulak.smartmoney.service.network.response.country

import com.molbulak.smartmoney.service.network.response.CrmError

data class CountryResponse(
    val code: Int,
    val result: List<Country>?,
    val error: CrmError?,
)

data class Country(
    val id: String,
    val iso_code: String,
    val name: String,
    val phone_code: String,
    val phone_length: String,
    val phone_mask: String,
    val phone_mask_small: String,
)