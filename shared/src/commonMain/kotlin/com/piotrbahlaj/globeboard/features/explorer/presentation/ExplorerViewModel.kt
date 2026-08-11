package com.piotrbahlaj.globeboard.features.explorer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotrbahlaj.globeboard.common.countries.data.models.Country
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository
import com.piotrbahlaj.globeboard.core.constants.Constants
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class ExplorerViewModel(
    private val repository: CountryRepository
) : ViewModel() {

    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    private val _isLoading = MutableStateFlow(true)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    @OptIn(FlowPreview::class)
    val uiState: StateFlow<ExplorerUiState> = combine(
        _allCountries,
        _searchQuery.debounce(300.milliseconds),
        _isLoading,
        _errorMessage
    ) { countries, query, isLoading, error ->
        when {
            error != null -> ExplorerUiState.Error(error)
            isLoading -> ExplorerUiState.Loading
            else -> {
                val filtered = if (query.isBlank()) {
                    countries
                } else {
                    countries.filter { it.name.contains(query, ignoreCase = true) }
                }
                ExplorerUiState.Success(filtered)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExplorerUiState.Loading
    )

    init {
        loadCountries()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getAllCountries()
            result.fold(
                onSuccess = { countries ->
                    _allCountries.value = countries
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _errorMessage.value = error.message ?: Constants.UNKNOWN_ERROR
                    _isLoading.value = false
                }
            )
        }
    }
}