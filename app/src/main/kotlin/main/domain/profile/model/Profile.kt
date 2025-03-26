package main.domain.profile.model

data class Profile(
    val account: Account,
    val battlePass: BattlePass,
    val stats: Stats
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
    val all: GameModeStats,
    val keyboardMouse: GameModeStats,
    val gamepad: GameModeStats,
    val touch: GameModeStats
)

data class GameModeStats(
    val overall: ModeStats,
    val solo: ModeStats,
    val duo: ModeStats,
    val squad: ModeStats,
    val ltm: ModeStats
)

data class ModeStats(
    val score: Int?,
    val scorePerMin: Double?,
    val scorePerMatch: Double?,
    val wins: Int?,
    val top3: Int?,
    val top5: Int?,
    val top6: Int?,
    val top10: Int?,
    val top12: Int?,
    val top25: Int?,
    val kills: Int?,
    val killsPerMin: Double?,
    val killsPerMatch: Double?,
    val deaths: Int?,
    val kd: Double?,
    val matches: Int?,
    val winRate: Double?,
    val minutesPlayed: Int?,
    val playersOutlived: Int?,
    val lastModified: String?
)