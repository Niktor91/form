package com.example.studentform.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary       = SwampGreen,
    secondary     = LightGreen,
    background    = Background,
    surface       = CardBackground,
    onPrimary     = CardBackground,
    onBackground  = TextPrimary,
    onSurface     = TextPrimary,
    outline       = BorderColor
)

@Composable
fun StudentFormTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}