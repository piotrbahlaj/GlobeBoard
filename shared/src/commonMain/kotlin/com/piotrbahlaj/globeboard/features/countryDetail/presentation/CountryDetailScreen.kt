package com.piotrbahlaj.globeboard.features.countryDetail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CountryDetailScreen(
    isoCode: String,
    viewModel: CountryDetailViewModel = koinViewModel(
        parameters = { parametersOf(isoCode) }
    )
) {
    val state by viewModel.uiState.collectAsState()

    when (val currentState = state) {
        is CountryDetailUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CountryDetailUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${currentState.message}")
            }
        }

        is CountryDetailUiState.Success -> {
            val country = currentState.country
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = country.name)
                Text(text = "Capital: ${country.capital ?: "N/A"}")
                Text(text = "Region: ${country.region}")
                Text(text = "Population: ${country.population}")
                Text(text = "Languages: ${country.languages.joinToString { it.name }}")
            }
        }
    }
}