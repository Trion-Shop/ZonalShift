package com.megapari.zonalshift.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.megapari.zonalshift.core.theme.*
import com.megapari.zonalshift.domain.model.*

@Composable
fun ZonalMechanicDetailModal(
    mechanic: ZonalPendulumMechanic,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShiftDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBlueBright)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = mechanic.mechanicTitle.uppercase(),
                    color = ShiftBlue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = mechanic.shiftArchetype,
                    color = ShiftRedBright,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ZonalMetricBadge(
                        label = "Slide Dist",
                        value = "${mechanic.lateralDisplacementMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    ZonalMetricBadge(
                        label = "Block Width",
                        value = "${mechanic.compactBlockWidthMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    ZonalMetricBadge(
                        label = "Squeeze Rate",
                        value = "${mechanic.turnoverForceRatePct.toInt()}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Theory",
                    color = ShiftWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = mechanic.theoreticalDescription,
                    color = ShiftSoftGray,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Synchronized Triggers",
                    color = ShiftBlueBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                mechanic.defensiveTriggers.forEach { trigger ->
                    Text(
                        text = "• $trigger",
                        color = ShiftMutedBlue,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Exposure Risks",
                    color = ShiftRedBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                mechanic.riskFactors.forEach { risk ->
                    Text(
                        text = "• $risk",
                        color = ShiftSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShiftDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShiftBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun DisplacementDrillDetailModal(
    drill: BlockDisplacementDrill,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShiftDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBlue)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = drill.drillName.uppercase(),
                    color = ShiftBlueBright,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = drill.focusSector,
                    color = ShiftMutedBlue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ZonalMetricBadge(
                        label = "Pitch Area",
                        value = drill.pitchSectorRatio,
                        modifier = Modifier.weight(1f)
                    )
                    ZonalMetricBadge(
                        label = "Slide Target",
                        value = "${drill.switchReactionTargetSec}s",
                        modifier = Modifier.weight(1f)
                    )
                    ZonalMetricBadge(
                        label = "Chain Size",
                        value = "${drill.playersCoordinatedCount} men",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Operational Rules",
                    color = ShiftWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.operationalRules.forEach { rule ->
                    Text(
                        text = "• $rule",
                        color = ShiftSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Tactical Checkpoints",
                    color = ShiftRedBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.tacticalCheckpoints.forEachIndexed { idx, pt ->
                    Text(
                        text = "${idx + 1}. $pt",
                        color = ShiftMutedBlue,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShiftDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShiftBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ZonalMatchDetailModal(
    match: ZonalMatchEncounter,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShiftDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShiftBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = match.encounterHeadline.uppercase(),
                        color = ShiftBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = match.finalScore,
                        color = ShiftWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${match.homeTeam} vs ${match.awayTeam}",
                    color = ShiftRedBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ZonalMetricBadge(
                        label = "Blocked Switches",
                        value = "${match.switchesBlockedPer90}",
                        modifier = Modifier.weight(1f)
                    )
                    ZonalMetricBadge(
                        label = "Space Allowed",
                        value = "${match.centralSpaceAllowancesPct}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Mastermind Assessment",
                    color = ShiftWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = match.tacticalReport,
                    color = ShiftSoftGray,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Decisive Shift Sequences",
                    color = ShiftBlueBright,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                match.decisiveShifts.forEach { shift ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = ShiftDarkElevated),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${shift.minute}' | ${shift.shiftLeader}",
                                    color = ShiftBlue,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "NEUTRALIZED",
                                    color = ShiftRedBright,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Ball: ${shift.ballPosition} (Target: ${shift.neutralizedAttacker})",
                                color = ShiftWhite,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Outcome: ${shift.defensiveOutcome}",
                                color = ShiftMutedBlue,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShiftDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShiftBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
