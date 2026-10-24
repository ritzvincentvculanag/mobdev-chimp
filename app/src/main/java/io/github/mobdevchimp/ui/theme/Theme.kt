package io.github.mobdevchimp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = macawBlue500,
    onPrimary = Color.White,
    secondary = featherGreen500,
    onSecondary = Color.White,
    tertiary = beeYellow400,
    onTertiary = featherGreen700,
    background = featherGreen700,
    onBackground = Color.White,
    surface = featherGreen700,
    onSurface = Color.White,
    error = cardinalRed500,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = macawBlue300,
    onPrimary = Color.White,
    secondary = featherGreen500,
    onSecondary = Color.White,
    tertiary = beeYellow400,
    onTertiary = featherGreen700,
    background = Color(0xFFF8FFF8),
    onBackground = featherGreen700,
    surface = Color.White,
    onSurface = featherGreen700,
    error = cardinalRed500,
    onError = Color.White
)

@Composable
fun MobdevchimpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}