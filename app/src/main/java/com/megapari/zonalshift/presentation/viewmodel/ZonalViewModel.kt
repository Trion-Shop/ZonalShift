package com.megapari.zonalshift.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megapari.zonalshift.data.InMemoryZonalShiftRepository
import com.megapari.zonalshift.domain.model.*
import com.megapari.zonalshift.domain.repository.ZonalShiftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ZonalUiState(
    val selectedTab: Int = 0,
    val mechanics: List<ZonalPendulumMechanic> = emptyList(),
    val drills: List<BlockDisplacementDrill> = emptyList(),
    val matches: List<ZonalMatchEncounter> = emptyList(),
    // Simulator
    val simBlockWidthMeters: Float = 30.0f,
    val simShiftSpeedMps: Float = 6.2f,
    val simSwitchTimeSec: Float = 3.2f,
    val simulationResult: ZonalShiftSimulationResult? = null,
    // Modals
    val activeMechanicModal: ZonalPendulumMechanic? = null,
    val activeDrillModal: BlockDisplacementDrill? = null,
    val activeMatchModal: ZonalMatchEncounter? = null
)

class ZonalViewModel(
    private val repository: ZonalShiftRepository = InMemoryZonalShiftRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ZonalUiState())
    val uiState: StateFlow<ZonalUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val mechs = repository.getZonalMechanics()
            val drs = repository.getDisplacementDrills()
            val mths = repository.getMatchEncounters()
            val sim = repository.calculateZonalSimulation(30.0f, 6.2f, 3.2f)

            _uiState.update {
                it.copy(
                    mechanics = mechs,
                    drills = drs,
                    matches = mths,
                    simulationResult = sim
                )
            }
        }
    }

    fun onSelectTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedTab = tabIndex) }
    }

    fun onSelectMechanic(mechanic: ZonalPendulumMechanic) {
        _uiState.update { it.copy(activeMechanicModal = mechanic) }
    }

    fun onSelectDrill(drill: BlockDisplacementDrill) {
        _uiState.update { it.copy(activeDrillModal = drill) }
    }

    fun onSelectMatch(match: ZonalMatchEncounter) {
        _uiState.update { it.copy(activeMatchModal = match) }
    }

    fun dismissModal() {
        _uiState.update {
            it.copy(
                activeMechanicModal = null,
                activeDrillModal = null,
                activeMatchModal = null
            )
        }
    }

    fun updateSimulation(blockWidth: Float, shiftSpeed: Float, switchTime: Float) {
        val sim = repository.calculateZonalSimulation(blockWidth, shiftSpeed, switchTime)
        _uiState.update {
            it.copy(
                simBlockWidthMeters = blockWidth,
                simShiftSpeedMps = shiftSpeed,
                simSwitchTimeSec = switchTime,
                simulationResult = sim
            )
        }
    }
}
