package com.megapari.zonalshift.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ShiftBlue,
    onPrimary = ShiftDarkBg,
    secondary = ShiftRed,
    onSecondary = ShiftWhite,
    tertiary = ShiftBlueBright,
    background = ShiftDarkBg,
    surface = ShiftDarkCard,
    onSurface = ShiftWhite
)

@Composable
fun ZonalShiftTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
