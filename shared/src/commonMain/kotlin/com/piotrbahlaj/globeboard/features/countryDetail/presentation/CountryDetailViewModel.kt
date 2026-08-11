package com.piotrbahlaj.globeboard.features.countryDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository
import com.piotrbahlaj.globeboard.core.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryDetailViewModel(
    private val isoCode: String,
    private val repository: CountryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CountryDetailUiState>(CountryDetailUiState.Loading)
    val uiState: StateFlow<CountryDetailUiState> = _uiState.asStateFlow()

    init {
        loadCountry()
    }

    private fun loadCountry() {
        viewModelScope.launch {
            _uiState.value = CountryDetailUiState.Loading
            val result = repository.getCountryByCode(isoCode)
            _uiState.value = result.fold(
                onSuccess = { country -> CountryDetailUiState.Success(country) },
                onFailure = { error -> CountryDetailUiState.Error(error.message ?: Constants.UNKNOWN_ERROR) }
            )
        }
    }
}