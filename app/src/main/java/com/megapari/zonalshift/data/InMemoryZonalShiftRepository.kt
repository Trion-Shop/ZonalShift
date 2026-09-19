package com.megapari.zonalshift.data

import com.megapari.zonalshift.domain.model.*
import com.megapari.zonalshift.domain.repository.ZonalShiftRepository

class InMemoryZonalShiftRepository : ZonalShiftRepository {

    override suspend fun getZonalMechanics(): List<ZonalPendulumMechanic> {
        return listOf(
            ZonalPendulumMechanic(
                id = "zm1",
                mechanicTitle = "Weak-Side Fullback Pinch",
                shiftArchetype = "Asymmetric Horizontal Slide",
                lateralDisplacementMeters = 18.5f,
                compactBlockWidthMeters = 32.0f,
                turnoverForceRatePct = 82.4f,
                theoreticalDescription = "The far-side full-back tucks inside into the central channel, maintaining a maximum 8-meter gap with the nearest center-back while surrendering the far touchline to maintain box density.",
                defensiveTriggers = listOf(
                    "Ball enters opponent's near-side half-space",
                    "Opponent winger receives facing away from the center",
                    "Defensive line leader issues step-and-tuck command"
                ),
                riskFactors = listOf(
                    "Rapid diagonal cross-field switch pass",
                    "Over-committing leaving far post completely unoccupied"
                )
            ),
            ZonalPendulumMechanic(
                id = "zm2",
                mechanicTitle = "Vertical Compression Step",
                shiftArchetype = "Offside Synchronized Squeeze",
                lateralDisplacementMeters = 8.0f,
                compactBlockWidthMeters = 28.5f,
                turnoverForceRatePct = 88.0f,
                theoreticalDescription = "As the opponent passes backward, the entire 10-man block surges forward simultaneously by 12 meters, collapsing the transit space available to opposing midfielders.",
                defensiveTriggers = listOf(
                    "Opponent midfielder forced to play backward pass",
                    "Opponent passer heads down and body orientation faces own goal",
                    "Zero immediate forward running threats on the shoulder"
                ),
                riskFactors = listOf(
                    "One defender lagging behind playing attackers onside",
                    "Blindside third-man runner sprinting against the forward slide"
                )
            ),
            ZonalPendulumMechanic(
                id = "zm3",
                mechanicTitle = "Touchline Funnel Overload",
                shiftArchetype = "Boundary Trap Squeeze",
                lateralDisplacementMeters = 22.0f,
                compactBlockWidthMeters = 25.0f,
                turnoverForceRatePct = 91.5f,
                theoreticalDescription = "Using the touchline as an extra defender, the full-back, near central midfielder, and winger form an impenetrable three-man cage to choke out the ball carrier.",
                defensiveTriggers = listOf(
                    "Pass delivered directly to the touchline chalk",
                    "Carrier controls with outer foot facing the sideline",
                    "Central cover shadow midfielder blocks inward pass vector"
                ),
                riskFactors = listOf(
                    "Foul conceded in dangerous crossing territory",
                    "Skillful nutmeg or line clip down the channel"
                )
            ),
            ZonalPendulumMechanic(
                id = "zm4",
                mechanicTitle = "Back-Three Sliding Axis",
                shiftArchetype = "Dynamic Center-Back Pendulum",
                lateralDisplacementMeters = 14.0f,
                compactBlockWidthMeters = 30.0f,
                turnoverForceRatePct = 85.0f,
                theoreticalDescription = "In a 3-5-2 or 3-4-3 system, the wide center-back steps out into the channel while the middle and far center-backs slide over to maintain central superiority.",
                defensiveTriggers = listOf(
                    "Wing-back is bypassed or caught high up the pitch",
                    "Opponent striker drifts into the channel between center-back and wing-back",
                    "Direct through ball played along the touchline channel"
                ),
                riskFactors = listOf(
                    "Vacated central box zone if middle CB fails to slide immediately",
                    "Mismatch in 1v1 speed against dynamic wingers"
                )
            )
        )
    }

    override suspend fun getDisplacementDrills(): List<BlockDisplacementDrill> {
        return listOf(
            BlockDisplacementDrill(
                id = "bd1",
                drillName = "4-Chain Synchronized Slide",
                focusSector = "Back-Four Lateral Pendulum",
                pitchSectorRatio = "Full Width x 30m Depth",
                switchReactionTargetSec = 2.4f,
                playersCoordinatedCount = 4,
                operationalRules = listOf(
                    "Four defenders connected via tactical elastic cord lines",
                    "Spacing between adjacent defenders must never exceed 9 meters",
                    "Attacking switch team alternates cross-field diagonal balls"
                ),
                tacticalCheckpoints = listOf(
                    "Near-side full-back engages while near center-back covers depth",
                    "Far center-back commands the horizontal line shift",
                    "Far-side full-back pinches beyond the center spot"
                )
            ),
            BlockDisplacementDrill(
                id = "bd2",
                drillName = "Half-Space Overload Squeeze",
                focusSector = "Midfield & Defensive Block Integration",
                pitchSectorRatio = "Half Pitch x 40m Depth",
                switchReactionTargetSec = 1.8f,
                playersCoordinatedCount = 8,
                operationalRules = listOf(
                    "8v6 overload simulation in the designated half-space corridor",
                    "Defense must force turnover within 4 touches or 5 seconds",
                    "Attackers receive bonus points for switching through central pocket"
                ),
                tacticalCheckpoints = listOf(
                    "Near-side central midfielder locks down the lateral lane",
                    "Defensive line shifts 5 meters laterally upon first touch",
                    "Goalkeeper adjusts positioning along the penalty spot arc"
                )
            ),
            BlockDisplacementDrill(
                id = "bd3",
                drillName = "Diagonal Switch Denial",
                focusSector = "Long Switch Interception & Aerial Repositioning",
                pitchSectorRatio = "Full Pitch x Full Width",
                switchReactionTargetSec = 3.1f,
                playersCoordinatedCount = 10,
                operationalRules = listOf(
                    "Opponent designated quarterback attempts 40-meter diagonal switches",
                    "Far-side full-back must arrive before ball touches the grass",
                    "Midfield line drops 6 meters to shield the second ball rebound"
                ),
                tacticalCheckpoints = listOf(
                    "Identify passer wind-up cue to begin displacement sprint early",
                    "Body shape half-turned to track both the flight and the runner",
                    "Clearance priority directed toward touchline or keeper"
                )
            )
        )
    }

    override suspend fun getMatchEncounters(): List<ZonalMatchEncounter> {
        return listOf(
            ZonalMatchEncounter(
                id = "ze1",
                encounterHeadline = "The Calderón Iron Pendulum",
                homeTeam = "Atlético Madrid",
                awayTeam = "Barcelona",
                finalScore = "2 - 0",
                defensiveMastermind = "Diego Simeone (Atlético 4-4-2)",
                switchesBlockedPer90 = 14,
                centralSpaceAllowancesPct = 8,
                tacticalReport = "Simeone's back-four and midfield-four functioned as an unyielding single unit. Every Barcelona pass to Messi was met by an instant 3-man zonal squeeze, neutralizing Camp Nou's passing machine.",
                decisiveShifts = listOf(
                    DecisiveShiftMoment(
                        minute = 36,
                        ballPosition = "Right half-space (Messi)",
                        shiftLeader = "Diego Godín",
                        neutralizedAttacker = "Lionel Messi",
                        defensiveOutcome = "Triple zonal collapse forcing turnover and Griezmann counter goal"
                    ),
                    DecisiveShiftMoment(
                        minute = 72,
                        ballPosition = "Left touchline (Neymar)",
                        shiftLeader = "Juanfran & Gabi",
                        neutralizedAttacker = "Neymar Jr",
                        defensiveOutcome = "Touchline cage recovery preventing inside cut"
                    )
                )
            ),
            ZonalMatchEncounter(
                id = "ze2",
                encounterHeadline = "The San Siro Steel Squeeze",
                homeTeam = "Inter Milan",
                awayTeam = "Barcelona",
                finalScore = "3 - 1",
                defensiveMastermind = "José Mourinho (Inter Milan)",
                switchesBlockedPer90 = 17,
                centralSpaceAllowancesPct = 11,
                tacticalReport = "Mourinho commanded a synchronized zonal shift where Zanetti and Cambiasso formed an impassable screen in front of Lucio and Samuel, cutting off all interior passing corridors.",
                decisiveShifts = listOf(
                    DecisiveShiftMoment(
                        minute = 30,
                        ballPosition = "Central circle",
                        shiftLeader = "Esteban Cambiasso",
                        neutralizedAttacker = "Xavi Hernández",
                        defensiveOutcome = "Zonal squeeze intercepting pass, launching Milito assist"
                    ),
                    DecisiveShiftMoment(
                        minute = 61,
                        ballPosition = "Edge of 18-yard box",
                        shiftLeader = "Lúcio",
                        neutralizedAttacker = "Zlatan Ibrahimović",
                        defensiveOutcome = "Synchronized offside step catching striker 2 meters offside"
                    )
                )
            ),
            ZonalMatchEncounter(
                id = "ze3",
                encounterHeadline = "The Lisbon Zonal Masterclass",
                homeTeam = "Chelsea",
                awayTeam = "Manchester City",
                finalScore = "1 - 0",
                defensiveMastermind = "Thomas Tuchel (Chelsea 3-4-2-1)",
                switchesBlockedPer90 = 12,
                centralSpaceAllowancesPct = 9,
                tacticalReport = "Tuchel's 5-man backline shifted with mathematical precision, denying De Bruyne and Foden half-space entry angles throughout 90 intense Champions League final minutes.",
                decisiveShifts = listOf(
                    DecisiveShiftMoment(
                        minute = 42,
                        ballPosition = "Left inside channel",
                        shiftLeader = "César Azpilicueta",
                        neutralizedAttacker = "Raheem Sterling",
                        defensiveOutcome = "Far-post sliding clearance denying guaranteed tap-in"
                    ),
                    DecisiveShiftMoment(
                        minute = 77,
                        ballPosition = "Right wing cross",
                        shiftLeader = "Antonio Rüdiger",
                        neutralizedAttacker = "Phil Foden",
                        defensiveOutcome = "Vital sliding block inside penalty box"
                    )
                )
            )
        )
    }

    override fun calculateZonalSimulation(
        blockWidthMeters: Float,
        shiftSpeedMps: Float,
        switchTimeSec: Float
    ): ZonalShiftSimulationResult {
        // Time needed for defense to shift across the pitch to cover weak side:
        // shiftDistance = standard pitch width (68m) - blockWidth
        val pitchWidth = 68.0f
        val distanceToShift = pitchWidth - blockWidthMeters
        val shiftTimeNeeded = distanceToShift / shiftSpeedMps

        // Weak side exposure risk: if shiftTimeNeeded > switchTimeSec, opponent arrives before block
        val delta = shiftTimeNeeded - switchTimeSec
        val exposureRisk = ((delta * 25f) + 35f).coerceIn(10f, 95f).toInt()

        // Space compression efficiency: tighter block yields higher central density
        val compressionEfficiency = ((50f - blockWidthMeters) * 2.8f + 50f).coerceIn(20f, 98f).toInt()

        val verdict = when {
            exposureRisk < 30 ->
                "IMPERMEABLE HORIZONTAL REPOSITIONING: Block slides rapidly across the pitch, neutralizing switch balls before attackers can orient."
            exposureRisk < 60 ->
                "BALANCED ZONE COVERAGE: Compact central presence maintained. Marginal risk against pinpoint 45-meter diagonal cross-field deliveries."
            else ->
                "VULNERABLE WEAK-SIDE: Block is too narrow and slow to shift. Opponent switch pass exposes isolated full-back in 1v2 overload."
        }

        return ZonalShiftSimulationResult(
            defensiveBlockWidthMeters = blockWidthMeters,
            shiftSpeedMps = shiftSpeedMps,
            attackerSwitchTimeSec = switchTimeSec,
            weakSideExposureRiskPct = exposureRisk,
            spaceCompressionEfficiencyPct = compressionEfficiency,
            tacticalVerdict = verdict
        )
    }
}
