package com.megapari.zonalshift.presentation.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
                                .width(205.dp)
                                .clickable { onSelectMechanic(mechanic) }
                                .border(1.dp, ShiftBorder, RoundedCornerShape(14.dp)),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = ShiftDarkCard)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = mechanic.mechanicTitle,
                                        color = ShiftWhite,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(ShiftDarkElevated)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "${mechanic.lateralDisplacementMeters}m",
                                            color = ShiftBlueBright,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Text(
                                    text = mechanic.shiftArchetype,
                                    color = ShiftRedBright,
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = mechanic.theoreticalDescription,
                                    color = ShiftSoftGray,
                                    fontSize = 10.sp,
                                    maxLines = 2,
                                    lineHeight = 13.sp
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
