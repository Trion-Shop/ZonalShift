package com.megapari.zonalshift.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// MEGAPARI PALETTE: Cyber Pulse Blue (#4896CF), Red (#CD4536), Dark (#020100 / #0A0D14), White (#FFFFFF)
val ShiftDarkBg = Color(0xFF070B12)
val ShiftDarkCard = Color(0xFF0F1726)
val ShiftDarkElevated = Color(0xFF162238)
val ShiftBorder = Color(0xFF223554)

val ShiftBlue = Color(0xFF4896CF)
val ShiftBlueBright = Color(0xFF67B5F0)
val ShiftBlueDark = Color(0xFF236594)

val ShiftRed = Color(0xFFCD4536)
val ShiftRedBright = Color(0xFFE85B4B)
val ShiftRedGlow = Color(0xFFFF6F61)

val ShiftWhite = Color(0xFFFFFFFF)
val ShiftMutedBlue = Color(0xFFA5C2DC)
val ShiftSoftGray = Color(0xFFD6DFE8)

val ShiftBgGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF0A1424),
        Color(0xFF070B12),
        Color(0xFF020100)
    )
)
