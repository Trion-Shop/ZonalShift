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
import com.megapari.zonalshift.domain.model.BlockDisplacementDrill
import com.megapari.zonalshift.presentation.ui.components.DisplacementDrillCard
import com.megapari.zonalshift.presentation.viewmodel.ZonalUiState

@Composable
fun DisplacementDrillsTab(
    uiState: ZonalUiState,
    onSelectDrill: (BlockDisplacementDrill) -> Unit,
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
                    text = "BLOCK DISPLACEMENT DRILLS",
                    color = ShiftBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Drilling the back-four and double pivot into a single synchronized elastic sliding unit.",
                    color = ShiftMutedBlue,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.drills, key = { it.id }) { drill ->
            DisplacementDrillCard(
                drill = drill,
                onClick = { onSelectDrill(drill) }
            )
        }
    }
}
