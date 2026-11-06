package com.bissaulife.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BissauGreen,
    secondary = BissauDarkBlue,
    background = BackgroundLight,
    surface = Color.White
)

private val DarkColors = darkColorScheme(
    primary = BissauGreen,
    secondary = BissauDarkBlue,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

@Composable
fun BissauLifeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
