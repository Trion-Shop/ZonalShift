package com.megapari.zonalshift.presentation.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.zonalshift.core.theme.*
import com.megapari.zonalshift.domain.model.ZonalMatchEncounter
import com.megapari.zonalshift.domain.model.ZonalPendulumMechanic
import com.megapari.zonalshift.presentation.ui.components.ZonalMatchCard
import com.megapari.zonalshift.presentation.viewmodel.ZonalUiState

@Composable
fun ZonalMatchesTab(
    uiState: ZonalUiState,
    onSelectMatch: (ZonalMatchEncounter) -> Unit,
    onSelectMechanic: (ZonalPendulumMechanic) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        // Carousel of Zonal Pendulum Archetypes
        item {
            Column {
                Text(
                    text = "DEFENSIVE PENDULUM ARCHETYPES",
                    color = ShiftBlueBright,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.mechanics, key = { it.id }) { mechanic ->
                        Card(
                            modifier = Modifier
                                .width(190.dp)
                                .clickable { onSelectMechanic(mechanic) }
                                .border(1.dp, ShiftBorder, RoundedCornerShape(14.dp)),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = ShiftDarkCard)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = mechanic.mechanicTitle,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ShiftWhite
                                )
                                Text(
                                    text = mechanic.shiftArchetype,
                                    fontSize = 10.sp,
                                    color = ShiftRedBright
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${mechanic.turnoverForceRatePct.toInt()}% force",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = ShiftBlueBright
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(ShiftDarkElevated)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "${mechanic.lateralDisplacementMeters}m slide",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = ShiftBlue
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = mechanic.theoreticalDescription,
                                    fontSize = 10.sp,
                                    color = ShiftSoftGray,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "LEGENDARY ZONAL BATTLES",
                    color = ShiftBlue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Tactical showdowns where Simeone, Mourinho, and Tuchel suffocated elite offenses.",
                    color = ShiftMutedBlue,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.matches, key = { it.id }) { match ->
            ZonalMatchCard(
                match = match,
                onClick = { onSelectMatch(match) }
            )
        }
    }
}
