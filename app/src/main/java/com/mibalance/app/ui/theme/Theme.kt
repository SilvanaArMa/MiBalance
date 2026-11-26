
package com.mibalance.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Verde,
    onPrimary = Blanco,

    secondary = Menta,
    onSecondary = Texto,

    tertiary = VerdeOscuro,
    onTertiary = Blanco,

    background = Fondo,
    onBackground = Texto,

    surface = Blanco,
    onSurface = Texto,

    error = Gasto,
    onError = Blanco
)

private val DarkColorScheme = darkColorScheme(
    primary = Verde,
    onPrimary = Blanco,

    secondary = Menta,
    onSecondary = Texto,

    tertiary = VerdeOscuro,
    onTertiary = Blanco,

    background = Texto,
    onBackground = Blanco,

    surface = VerdeOscuro,
    onSurface = Blanco,

    error = Gasto,
    onError = Blanco
)

@Composable
fun MiBalanceTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
