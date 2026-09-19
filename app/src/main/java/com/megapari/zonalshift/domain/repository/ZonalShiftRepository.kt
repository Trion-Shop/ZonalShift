package com.megapari.zonalshift.domain.repository

import com.megapari.zonalshift.domain.model.*

interface ZonalShiftRepository {
    suspend fun getZonalMechanics(): List<ZonalPendulumMechanic>
    suspend fun getDisplacementDrills(): List<BlockDisplacementDrill>
    suspend fun getMatchEncounters(): List<ZonalMatchEncounter>
    fun calculateZonalSimulation(
        blockWidthMeters: Float,
        shiftSpeedMps: Float,
        switchTimeSec: Float
    ): ZonalShiftSimulationResult
}
