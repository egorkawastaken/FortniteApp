package main.presentation.profile.mapper

import main.common.utils.Mapper
import main.domain.profile.model.GameModeStats
import main.domain.profile.model.ModeStats
import main.domain.profile.model.Stats
import main.presentation.profile.model.GameModeStatsUiModel
import main.presentation.profile.model.ModeStatsUiModel
import main.presentation.profile.model.StatsUiModel
import javax.inject.Inject

class StatsUiMapper @Inject constructor(
    private val gameModeStatsMapper: GameModeStatsUiMapper
) : Mapper<Stats, StatsUiModel> {
    override fun map(from: Stats): StatsUiModel {
        return StatsUiModel(
            all = gameModeStatsMapper.map(from.all),
            keyboardMouse = gameModeStatsMapper.map(from.keyboardMouse),
            gamepad = gameModeStatsMapper.map(from.gamepad),
            touch = gameModeStatsMapper.map(from.touch)
        )
    }
}

class GameModeStatsUiMapper @Inject constructor(
    private val modeStatsMapper: ModeStatsUiMapper
) : Mapper<GameModeStats?, List<GameModeStatsUiModel>> {
    override fun map(from: GameModeStats?): List<GameModeStatsUiModel> {
        return from?.let {
            listOf(
                "OVERALL" to it.overall,
                "SOLO" to it.solo,
                "DUO" to it.duo,
                "SQUAD" to it.squad,
                "LTM" to it.ltm
            ).map { (mode, stats) -> GameModeStatsUiModel(mode, modeStatsMapper.map(stats)) }
        } ?: emptyList()
    }
}

class ModeStatsUiMapper : Mapper<ModeStats, List<ModeStatsUiModel>> {
    override fun map(from: ModeStats): List<ModeStatsUiModel> {
        return listOf(
            "Score" to from.score,
            "Score per Min" to from.scorePerMin?.toInt(),
            "Score per Match" to from.scorePerMatch?.toInt(),
            "Wins" to from.wins,
            "Top 3" to from.top3,
            "Top 5" to from.top5,
            "Top 6" to from.top6,
            "Top 10" to from.top10,
            "Top 12" to from.top12,
            "Top 25" to from.top25,
            "Kills" to from.kills,
            "Kills per Min" to from.killsPerMin?.toInt(),
            "Kills per Match" to from.killsPerMatch?.toInt(),
            "Deaths" to from.deaths,
            "K/D Ratio" to from.kd?.toInt(),
            "Matches Played" to from.matches,
            "Win Rate (%)" to from.winRate?.toInt(),
            "Minutes Played" to from.minutesPlayed,
            "Players Outlived" to from.playersOutlived
        ).mapNotNull { (name, value) -> value?.let { ModeStatsUiModel(name, it) } }
    }
}