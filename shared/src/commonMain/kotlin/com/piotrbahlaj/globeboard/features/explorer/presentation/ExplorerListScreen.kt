package com.piotrbahlaj.globeboard.features.explorer.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piotrbahlaj.globeboard.core.constants.Constants
import com.piotrbahlaj.globeboard.features.explorer.presentation.ui.CountryRow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExplorerListScreen(
    onCountryClick: (String) -> Unit,
    viewModel: ExplorerViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            placeholder = { Text(Constants.SEARCH_TEXT_FIELD_LABEL) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(percent = 50),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedBorderColor = androidx.compose.ui.graphics.Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        when (val currentState = state) {
            is ExplorerUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ExplorerUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${currentState.message}")
                }
            }

            is ExplorerUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
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
}