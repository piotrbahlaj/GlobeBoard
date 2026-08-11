package com.piotrbahlaj.globeboard.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = InkNavy,
    onPrimary = Parchment,
    secondary = ChartTeal,
    onSecondary = Parchment,
    tertiary = CompassGold,
    onTertiary = InkNavy,
    background = Parchment,
    onBackground = InkNavy,
    surface = Parchment,
    onSurface = InkNavy,
    surfaceVariant = ParchmentDim,
    error = ErrorRed
)

private val DarkColors = darkColorScheme(
    primary = GoldBright,
    onPrimary = DarkBackground,
    secondary = TealLight,
    onSecondary = DarkBackground,
    tertiary = GoldBright,
    onTertiary = DarkBackground,
    background = DarkBackground,
    onBackground = Parchment,
    surface = DarkSurface,
    onSurface = ParchmentDim,
    surfaceVariant = DarkSurfaceVariant,
    error = ErrorRed
)

@Composable
fun GlobeBoardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = GlobeBoardShapes,
        content = content
    )
}