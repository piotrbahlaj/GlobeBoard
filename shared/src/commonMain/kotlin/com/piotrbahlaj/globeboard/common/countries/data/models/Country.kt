package com.piotrbahlaj.globeboard.common.countries.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    @SerialName("alpha3Code") val isoCode: String,
    val capital: String? = null,
    val region: String,
    val population: Long,
    val flags: Flags,
    val currencies: List<Currency> = emptyList(),
    val languages: List<Language> = emptyList()
)