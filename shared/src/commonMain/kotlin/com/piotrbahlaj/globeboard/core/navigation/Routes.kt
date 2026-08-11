package com.piotrbahlaj.globeboard.core.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Dashboard : Routes

    @Serializable
    data object ExplorerList : Routes

    @Serializable
    data class CountryDetail(val isoCode: String) : Routes

    @Serializable
    data object Settings : Routes
}