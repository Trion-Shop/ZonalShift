package com.megapari.zonalshift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.megapari.zonalshift.core.theme.ZonalShiftTheme
import com.megapari.zonalshift.presentation.ui.screens.ZonalShiftScreen
import com.megapari.zonalshift.presentation.viewmodel.ZonalViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ZonalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZonalShiftTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) {
                    ZonalShiftScreen(viewModel = viewModel)
                }
            }
        }
    }
}
