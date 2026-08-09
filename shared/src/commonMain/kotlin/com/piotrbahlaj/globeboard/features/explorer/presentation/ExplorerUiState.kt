package com.piotrbahlaj.globeboard.features.explorer.presentation

import com.piotrbahlaj.globeboard.common.countries.data.models.Country

sealed interface ExplorerUiState {
    data object Loading : ExplorerUiState
    data class Success(val countries: List<Country>) : ExplorerUiState
    data class Error(val message: String) : ExplorerUiState
}