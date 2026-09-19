package com.megapari.zonalshift.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.zonalshift.core.theme.*
import com.megapari.zonalshift.presentation.ui.components.*
import com.megapari.zonalshift.presentation.ui.tabs.*
import com.megapari.zonalshift.presentation.viewmodel.ZonalViewModel

@Composable
fun ZonalShiftScreen(
    viewModel: ZonalViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val tabTitles = listOf("Simulator", "Mechanics", "Drills", "Matches")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ShiftBgGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ZonalHeader(
                title = "Zonal Shift",
                subtitle = "Defensive Pendulum & Space Compression Lab"
            )

            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = ShiftDarkBg,
                contentColor = ShiftBlueBright,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                        color = ShiftBlueBright,
                        height = 3.dp
                    )
                },
                divider = {
                    HorizontalDivider(color = ShiftBorder.copy(alpha = 0.5f))
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.onSelectTab(index) },
                        text = {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = if (uiState.selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                                color = if (uiState.selectedTab == index) ShiftBlueBright else ShiftMutedBlue
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedTab) {
                    0 -> ZonalSimulatorTab(
                        uiState = uiState,
                        onUpdateSim = { width, speed, time ->
                            viewModel.updateSimulation(width, speed, time)
                        }
                    )
                    1 -> ZonalMechanicsTab(
                        uiState = uiState,
                        onSelectMechanic = { viewModel.onSelectMechanic(it) }
                    )
                    2 -> DisplacementDrillsTab(
                        uiState = uiState,
                        onSelectDrill = { viewModel.onSelectDrill(it) }
                    )
                    3 -> ZonalMatchesTab(
                        uiState = uiState,
                        onSelectMatch = { viewModel.onSelectMatch(it) }
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
