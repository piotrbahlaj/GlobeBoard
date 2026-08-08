package com.piotrbahlaj.globeboard.features.explorer.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piotrbahlaj.globeboard.common.countries.data.models.Country
import com.piotrbahlaj.globeboard.features.explorer.presentation.ExplorerUiState
import com.piotrbahlaj.globeboard.features.explorer.presentation.ExplorerViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExplorerListScreen(
    onCountryClick: (String) -> Unit,
    viewModel: ExplorerViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (val currentState = state) {
        is ExplorerUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ExplorerUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${currentState.message}")
            }
        }

        is ExplorerUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(currentState.countries) { country ->
                    CountryRow(
                        country = country,
                        onClick = { onCountryClick(country.isoCode) }
                    )
                }
            }
        }
    }

}

@Composable
private fun CountryRow(
    country: Country,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = country.name)
        Text(text = country.capital ?: "N/A")
    }
}