package com.megapari.zonalshift.presentation.ui.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.zonalshift.core.theme.ShiftBlue
import com.megapari.zonalshift.core.theme.ShiftMutedBlue
import com.megapari.zonalshift.domain.model.ZonalMatchEncounter
import com.megapari.zonalshift.presentation.ui.components.ZonalMatchCard
import com.megapari.zonalshift.presentation.viewmodel.ZonalUiState

@Composable
fun ZonalMatchesTab(
    uiState: ZonalUiState,
    onSelectMatch: (ZonalMatchEncounter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        item {
            Column {
                Text(
                    text = "LEGENDARY ZONAL BATTLES",
                    color = ShiftBlue,
                    fontSize = 16.sp,
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
