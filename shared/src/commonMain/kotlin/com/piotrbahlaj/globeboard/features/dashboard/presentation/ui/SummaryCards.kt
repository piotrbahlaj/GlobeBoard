package com.piotrbahlaj.globeboard.features.dashboard.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piotrbahlaj.globeboard.core.constants.Constants
import com.piotrbahlaj.globeboard.core.utilities.formatPopulation
import com.piotrbahlaj.globeboard.features.dashboard.presentation.DashboardUiState

@Composable
fun SummaryCards(state: DashboardUiState.Success) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(
            label = Constants.COUNTRIES,
            value = state.totalCountries.toString(),
            modifier = Modifier.weight(1f)
        )
        StatCard(
            label = Constants.REGIONS,
            value = state.totalRegions.toString(),
            modifier = Modifier.weight(1f)
        )
        StatCard(
            label = Constants.POPULATION,
            value = formatPopulation(state.totalPopulation),
            modifier = Modifier.weight(1f)
        )
    }
}