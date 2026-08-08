package com.piotrbahlaj.globeboard.features.explorer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository
import com.piotrbahlaj.globeboard.core.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExplorerViewModel(
    private val repository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExplorerUiState>(ExplorerUiState.Loading)
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _uiState.value = ExplorerUiState.Loading
            val result = repository.getAllCountries()
            _uiState.value = result.fold(
                onSuccess = { countries -> ExplorerUiState.Success(countries) },
                onFailure = { error -> ExplorerUiState.Error(error.message ?: Constants.UNKNOWN_ERROR) }
            )
        }
    }
}