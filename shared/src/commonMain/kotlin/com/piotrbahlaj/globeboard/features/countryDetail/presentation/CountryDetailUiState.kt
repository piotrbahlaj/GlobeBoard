package com.piotrbahlaj.globeboard.features.countryDetail.presentation

import com.piotrbahlaj.globeboard.common.countries.data.models.Country

sealed interface CountryDetailUiState {
    data object Loading : CountryDetailUiState
    data class Success(val country: Country) : CountryDetailUiState
    data class Error(val message: String) : CountryDetailUiState

}