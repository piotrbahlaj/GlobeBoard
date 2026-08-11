package com.piotrbahlaj.globeboard.features.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.piotrbahlaj.globeboard.core.constants.Constants

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
    ) {
        Text(
            text = Constants.SETTINGS,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = Constants.TOGGLE_THEME)
            Switch(checked = isDarkTheme, onCheckedChange = { onToggleTheme() })
        }
    }
}