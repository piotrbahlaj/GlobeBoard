package com.piotrbahlaj.globeboard.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrbahlaj.globeboard.common.countries.data.models.Country
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository
import com.piotrbahlaj.globeboard.core.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: CountryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            val result = repository.getAllCountries()
            _uiState.value = result.fold(
                onSuccess = { countries -> buildDashboardState(countries) },
                onFailure = { error -> DashboardUiState.Error(error.message ?: Constants.UNKNOWN_ERROR) }
            )
        }
    }

    private fun buildDashboardState(countries: List<Country>): DashboardUiState.Success {
        return DashboardUiState.Success(
            totalCountries = countries.size,
            totalPopulation = countries.sumOf { it.population },
            totalRegions = countries.map { it.region }.distinct().size,
            topByPopulation = countries.sortedByDescending { it.population }.take(5)
        )
    }
}