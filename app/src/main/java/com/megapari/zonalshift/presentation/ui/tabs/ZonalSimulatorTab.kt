package com.megapari.zonalshift.presentation.ui.tabs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.zonalshift.core.theme.*
import com.megapari.zonalshift.presentation.ui.components.ZonalMetricBadge
import com.megapari.zonalshift.presentation.viewmodel.ZonalUiState

@Composable
fun ZonalSimulatorTab(
    uiState: ZonalUiState,
    onUpdateSim: (Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val sim = uiState.simulationResult

    val infiniteTransition = rememberInfiniteTransition(label = "pendulum")
    val shiftOffsetFraction by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shiftOffset"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "ZONAL PENDULUM SLIDE SIMULATOR",
            color = ShiftBlue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
        )
        Text(
            text = "Model defensive compact block sliding velocity vs opponent cross-field switch passes.",
            color = ShiftMutedBlue,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Visual Canvas Pitch Simulation
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = ShiftDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Pitch outlines
                    drawRect(
                        color = ShiftBorder.copy(alpha = 0.4f),
                        topLeft = Offset(16.dp.toPx(), 16.dp.toPx()),
                        size = Size(w - 32.dp.toPx(), h - 32.dp.toPx()),
                        style = Stroke(width = 1.5.dp.toPx())
                    )

                    // 5 Tactical Pitch Channels (Left Wing, Left Half-Space, Center, Right Half-Space, Right Wing)
                    val channelWidth = (w - 32.dp.toPx()) / 5f
                    for (i in 1..4) {
                        val lineX = 16.dp.toPx() + i * channelWidth
                        drawLine(
                            color = ShiftBorder.copy(alpha = 0.25f),
                            start = Offset(lineX, 16.dp.toPx()),
                            end = Offset(lineX, h - 16.dp.toPx()),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(6f, 6f))
                        )
                    }

                    // Compact Defensive Block sliding across horizontal axis
                    // Width scales with uiState.simBlockWidthMeters (20m to 45m)
                    val blockWidthPx = (uiState.simBlockWidthMeters / 68f) * (w - 32.dp.toPx())
                    val minBlockLeft = 16.dp.toPx()
                    val maxBlockLeft = (w - 16.dp.toPx()) - blockWidthPx
                    val currentBlockLeft = minBlockLeft + shiftOffsetFraction * (maxBlockLeft - minBlockLeft)

                    // Defensive Compact Block Rect
                    drawRect(
                        color = ShiftBlueDark.copy(alpha = 0.35f),
                        topLeft = Offset(currentBlockLeft, h * 0.4f),
                        size = Size(blockWidthPx, h * 0.42f)
                    )
                    drawRect(
                        color = ShiftBlue.copy(alpha = 0.8f),
                        topLeft = Offset(currentBlockLeft, h * 0.4f),
                        size = Size(blockWidthPx, h * 0.42f),
                        style = Stroke(width = 1.5.dp.toPx())
                    )

                    // Draw 4 Backline Defenders inside the sliding block
                    val defSpacing = blockWidthPx / 4f
                    for (i in 0 until 4) {
                        val defX = currentBlockLeft + (i + 0.5f) * defSpacing
                        val defY = h * 0.72f

                        drawCircle(
                            color = ShiftBlueBright,
                            center = Offset(defX, defY),
                            radius = 7.dp.toPx()
                        )
                    }

                    // Draw 4 Midfield Line Defenders
                    for (i in 0 until 4) {
                        val midX = currentBlockLeft + (i + 0.5f) * defSpacing
                        val midY = h * 0.5f

                        drawCircle(
                            color = ShiftBlue,
                            center = Offset(midX, midY),
                            radius = 6.dp.toPx()
                        )
                    }

                    // Opponent Switch Ball Vector (Red curved pass from one wing to opposite wing)
                    val ballStartX = currentBlockLeft + blockWidthPx * 0.8f
                    val ballStartY = h * 0.28f

                    val targetSwitchX = if (currentBlockLeft > w * 0.4f) 35.dp.toPx() else w - 35.dp.toPx()
                    val targetSwitchY = h * 0.32f

                    // Draw Attacker Ball Carrier
                    drawCircle(
                        color = ShiftRed,
                        center = Offset(ballStartX, ballStartY),
                        radius = 8.dp.toPx()
                    )

                    // Draw Weak Side Free Attacker
                    drawCircle(
                        color = ShiftRedBright,
                        center = Offset(targetSwitchX, targetSwitchY),
                        radius = 8.dp.toPx()
                    )

                    // Curved switch pass trajectory line
                    drawLine(
                        color = ShiftRedBright.copy(alpha = 0.7f),
                        start = Offset(ballStartX, ballStartY),
                        end = Offset(targetSwitchX, targetSwitchY),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "■ Zonal Block (Blue)",
                        color = ShiftBlueBright,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "● Opponent Switch (Red)",
                        color = ShiftRedBright,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sliders & Controls
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ShiftDarkCard),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBorder)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Defensive Block Compact Width",
                        color = ShiftWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${uiState.simBlockWidthMeters.toInt()} m",
                        color = ShiftBlueBright,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simBlockWidthMeters,
                    onValueChange = { onUpdateSim(it, uiState.simShiftSpeedMps, uiState.simSwitchTimeSec) },
                    valueRange = 22f..44f,
                    colors = SliderDefaults.colors(
                        thumbColor = ShiftBlueBright,
                        activeTrackColor = ShiftBlueBright,
                        inactiveTrackColor = ShiftDarkElevated
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Horizontal Shift Speed (m/s)",
                        color = ShiftWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("%.1f m/s", uiState.simShiftSpeedMps),
                        color = ShiftBlueBright,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simShiftSpeedMps,
                    onValueChange = { onUpdateSim(uiState.simBlockWidthMeters, it, uiState.simSwitchTimeSec) },
                    valueRange = 4.0f..8.5f,
                    colors = SliderDefaults.colors(
                        thumbColor = ShiftBlue,
                        activeTrackColor = ShiftBlue,
                        inactiveTrackColor = ShiftDarkElevated
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Opponent Switch Flight Time",
                        color = ShiftWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("%.1f s", uiState.simSwitchTimeSec),
                        color = ShiftRedBright,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simSwitchTimeSec,
                    onValueChange = { onUpdateSim(uiState.simBlockWidthMeters, uiState.simShiftSpeedMps, it) },
                    valueRange = 1.8f..4.5f,
                    colors = SliderDefaults.colors(
                        thumbColor = ShiftRedBright,
                        activeTrackColor = ShiftRedBright,
                        inactiveTrackColor = ShiftDarkElevated
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Simulation Output
        if (sim != null) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ShiftDarkElevated),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBlueBright)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ZONAL EFFICIENCY ANALYSIS",
                        color = ShiftBlueBright,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ZonalMetricBadge(
                            label = "Exposure Risk",
                            value = "${sim.weakSideExposureRiskPct}%",
                            modifier = Modifier.weight(1f)
                        )
                        ZonalMetricBadge(
                            label = "Central Squeeze",
                            value = "${sim.spaceCompressionEfficiencyPct}%",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = sim.tacticalVerdict,
                        color = ShiftSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
