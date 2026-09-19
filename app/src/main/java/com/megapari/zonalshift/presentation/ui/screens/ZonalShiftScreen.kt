package com.megapari.zonalshift.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.zonalshift.core.theme.*
import com.megapari.zonalshift.presentation.ui.components.*
import com.megapari.zonalshift.presentation.ui.tabs.*
import com.megapari.zonalshift.presentation.viewmodel.ZonalViewModel

data class ZonalNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun ZonalShiftScreen(
    viewModel: ZonalViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val navItems = listOf(
        ZonalNavItem("Shifts", Icons.Default.SportsSoccer),
        ZonalNavItem("Pendulum", Icons.Default.GridOn),
        ZonalNavItem("Mechanics", Icons.Default.Security),
        ZonalNavItem("Drills", Icons.Default.FitnessCenter)
    )

    Scaffold(
        bottomBar = {
            // Cyberpunk Holographic Bottom Dock with cyan glowing borders
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                shape = RoundedCornerShape(22.dp),
                color = ShiftDarkCard.copy(alpha = 0.95f),
                shadowElevation = 14.dp,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, ShiftBlue.copy(alpha = 0.6f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEachIndexed { index, item ->
                        val isSelected = uiState.selectedTab == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) ShiftDarkElevated else Color.Transparent)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { viewModel.onSelectTab(index) }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    tint = if (isSelected) ShiftBlueBright else ShiftMutedBlue.copy(alpha = 0.5f),
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = item.title,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Normal,
                                    color = if (isSelected) ShiftWhite else ShiftMutedBlue.copy(alpha = 0.65f)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(ShiftBgGradient)
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ZonalHeader(
                    title = "Zonal Shift",
                    subtitle = "Defensive Pendulum & Space Compression Lab"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    when (uiState.selectedTab) {
                        0 -> ZonalMatchesTab(
                            uiState = uiState,
                            onSelectMatch = { viewModel.onSelectMatch(it) },
                            onSelectMechanic = { viewModel.onSelectMechanic(it) }
                        )
                        1 -> ZonalSimulatorTab(
                            uiState = uiState,
                            onUpdateSim = { width, speed, time ->
                                viewModel.updateSimulation(width, speed, time)
                            }
                        )
                        2 -> ZonalMechanicsTab(
                            uiState = uiState,
                            onSelectMechanic = { viewModel.onSelectMechanic(it) }
                        )
                        3 -> DisplacementDrillsTab(
                            uiState = uiState,
                            onSelectDrill = { viewModel.onSelectDrill(it) }
                        )
                    }
                }
            }

            // Active Deep Dive Modals
            uiState.activeMechanicModal?.let { mechanic ->
                ZonalMechanicDetailModal(
                    mechanic = mechanic,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeDrillModal?.let { drill ->
                DisplacementDrillDetailModal(
                    drill = drill,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeMatchModal?.let { match ->
                ZonalMatchDetailModal(
                    match = match,
                    onDismiss = { viewModel.dismissModal() }
                )
            }
        }
    }
}
