package com.example.mytodo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
//다크모드, 라이트모드 지원
private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    surface = DarkSurface,
)

private val LightColorScheme = lightColorScheme(
    background = LightBackground,
    surface = LightSurface,
)

@Composable
fun MyTodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

