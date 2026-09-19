package com.megapari.zonalshift.domain.model

data class ZonalPendulumMechanic(
    val id: String,
    val mechanicTitle: String,
    val shiftArchetype: String, // Weak-Side Pinch, Vertical Compression Step, Touchline Overload Shift, Back-Three Sliding Axis
    val lateralDisplacementMeters: Float,
    val compactBlockWidthMeters: Float,
    val turnoverForceRatePct: Float,
    val theoreticalDescription: String,
    val defensiveTriggers: List<String>,
    val riskFactors: List<String>
)

data class BlockDisplacementDrill(
    val id: String,
    val drillName: String,
    val focusSector: String, // 4-Chain Synchronized Slide, Half-Space Overload Squeeze, Diagonal Switch Denial
    val pitchSectorRatio: String,
    val switchReactionTargetSec: Float,
    val playersCoordinatedCount: Int,
    val operationalRules: List<String>,
    val tacticalCheckpoints: List<String>
)

data class ZonalMatchEncounter(
    val id: String,
    val encounterHeadline: String,
    val homeTeam: String,
    val awayTeam: String,
    val finalScore: String,
    val defensiveMastermind: String,
    val switchesBlockedPer90: Int,
    val centralSpaceAllowancesPct: Int,
    val tacticalReport: String,
    val decisiveShifts: List<DecisiveShiftMoment>
)

data class DecisiveShiftMoment(
    val minute: Int,
    val ballPosition: String,
    val shiftLeader: String,
    val neutralizedAttacker: String,
    val defensiveOutcome: String
)

data class ZonalShiftSimulationResult(
    val defensiveBlockWidthMeters: Float,
    val shiftSpeedMps: Float,
    val attackerSwitchTimeSec: Float,
    val weakSideExposureRiskPct: Int,
    val spaceCompressionEfficiencyPct: Int,
    val tacticalVerdict: String
)
