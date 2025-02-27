package main.domain.profile.model

data class Profile(
    val text: String
)

data class Account(
    val id: String,
    val name: String
)

data class BattlePass(
    val level: Int,
    val progress: Int
)

data class Stats(
    val all: GameModeStats
)

data class GameModeStats(
    val overall: ModeStats,
    val solo: ModeStats,
    val duo: ModeStats,
    val squad: ModeStats
)

data class ModeStats(
    val score: Int? = null,
    val wins: Int? = null,
    val kills: Int? = null,
    val kd: Double? = null,
    val matches: Int? = null
)