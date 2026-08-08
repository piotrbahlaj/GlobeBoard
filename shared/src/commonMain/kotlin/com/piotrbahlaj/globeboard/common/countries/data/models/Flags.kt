package com.piotrbahlaj.globeboard.common.countries.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val svg: String,
    val png: String
)