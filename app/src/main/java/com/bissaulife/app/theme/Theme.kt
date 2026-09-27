package com.bissaulife.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = BissauGreen,
    secondary = BissauDarkBlue,
    background = BackgroundLight,
    surface = androidx.compose.ui.graphics.Color.White
)

@Composable
fun BissauLifeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
