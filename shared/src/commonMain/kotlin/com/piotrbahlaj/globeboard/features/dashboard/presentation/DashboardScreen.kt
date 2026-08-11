package com.piotrbahlaj.globeboard.features.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.piotrbahlaj.globeboard.core.constants.Constants
import com.piotrbahlaj.globeboard.features.dashboard.presentation.ui.SummaryCards
import com.piotrbahlaj.globeboard.features.dashboard.presentation.ui.TopCountryRow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    onCountryClick: (String) -> Unit,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (val currentState = state) {
        is DashboardUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DashboardUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${currentState.message}")
            }
        }

        is DashboardUiState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    SummaryCards(state = currentState)
                }
                item {
                    Text(text = Constants.TOP_POPULATION_LABEL)
                }
                items(currentState.topByPopulation) { country ->
                    TopCountryRow(
                        country = country,
                        onClick = { onCountryClick(country.isoCode) }
                    )
                }
            }
        }
    }
}


