package main.presentation.profile.model

data class StatsUiModel(
    val all: List<GameModeStatsUiModel>?,
    val keyboardMouse: List<GameModeStatsUiModel>?,
    val gamepad: List<GameModeStatsUiModel>?,
    val touch: List<GameModeStatsUiModel>?,
)

data class GameModeStatsUiModel(
    val mode: String,
    val stats: List<ModeStatsUiModel>
)

data class ModeStatsUiModel(
    val stat: String,
    val value: Int
)