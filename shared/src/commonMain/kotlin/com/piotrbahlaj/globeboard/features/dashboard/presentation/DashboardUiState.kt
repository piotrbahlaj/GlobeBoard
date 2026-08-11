package com.piotrbahlaj.globeboard.features.dashboard.presentation

import com.piotrbahlaj.globeboard.common.countries.data.models.Country

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(
        val totalCountries: Int,
        val totalPopulation: Long,
        val totalRegions: Int,
        val topByPopulation: List<Country>
    ) : DashboardUiState

    data class Error(val message: String) : DashboardUiState
}