package com.piotrbahlaj.globeboard.features.countryDetail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.piotrbahlaj.globeboard.core.constants.Constants
import com.piotrbahlaj.globeboard.core.utilities.formatPopulation
import com.piotrbahlaj.globeboard.features.countryDetail.presentation.ui.DetailRow
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CountryDetailUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${currentState.message}")
            }
        }

        is CountryDetailUiState.Success -> {
            val country = currentState.country
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = country.name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        DetailRow(
                            label = Constants.CAPITAL,
                            value = country.capital ?: "N/A"
                        )
                        DetailRow(
                            label = Constants.REGION,
                            value = country.region
                        )
                        DetailRow(
                            label = Constants.POPULATION,
                            value = formatPopulation(country.population, abbreviated = false)
                        )
                        DetailRow(
                            label = Constants.LANGUAGES,
                            value = country.languages.joinToString { it.name })
                    }
                }
            }
        }
    }
}

