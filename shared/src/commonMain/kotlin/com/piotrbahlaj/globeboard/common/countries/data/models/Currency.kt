package com.piotrbahlaj.globeboard.common.countries.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val code: String,
    val name: String,
    val symbol: String? = null
)