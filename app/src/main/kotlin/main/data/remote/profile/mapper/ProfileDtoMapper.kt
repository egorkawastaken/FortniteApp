package main.data.remote.profile.mapper

import main.common.utils.Mapper
import main.data.remote.profile.model.AccountDto
import main.data.remote.profile.model.BattlePassDto
import main.data.remote.profile.model.GameModeStatsDto
import main.data.remote.profile.model.ModeStatsDto
import main.data.remote.profile.model.ProfileDto
import main.data.remote.profile.model.StatsDto
import main.domain.profile.model.Account
import main.domain.profile.model.BattlePass
import main.domain.profile.model.GameModeStats
import main.domain.profile.model.ModeStats
import main.domain.profile.model.Profile
import main.domain.profile.model.Stats
import javax.inject.Inject

class ProfileDtoMapper @Inject constructor(
    private val accountDtoMapper: AccountDtoMapper,
    private val battlePassDtoMapper: BattlePassDtoMapper,
    private val statsDtoMapper: StatsDtoMapper
) : Mapper<ProfileDto, Profile> {
    override fun map(from: ProfileDto): Profile {
        val account = accountDtoMapper.map(from.accountDto)
        val battlePass = battlePassDtoMapper.map(from.battlePassDto)
        val stats = statsDtoMapper.map(from.statsDto)

        return Profile(
            text = """
                Account: ${account.name} (ID: ${account.id})
                Battle Pass: Level ${battlePass.level}, Progress: ${battlePass.progress}%
                Stats (All Modes): $stats
            """.trimIndent()
        )
    }
}

class AccountDtoMapper : Mapper<AccountDto, Account> {
    override fun map(from: AccountDto) = Account(
        id = from.id,
        name = from.name
    )
}

class BattlePassDtoMapper : Mapper<BattlePassDto, BattlePass> {
    override fun map(from: BattlePassDto) = BattlePass(
        level = from.level,
        progress = from.progress
    )
}

class StatsDtoMapper @Inject constructor(
    private val gameModeStatsMapper: GameModeStatsDtoMapper
) : Mapper<StatsDto, Stats> {
    override fun map(from: StatsDto) = Stats(
        all = gameModeStatsMapper.map(from.all)
    )
}

class GameModeStatsDtoMapper @Inject constructor(private val modeStatsMapper: ModeStatsDtoMapper) :
    Mapper<GameModeStatsDto, GameModeStats> {
    override fun map(from: GameModeStatsDto) = GameModeStats(
        overall = modeStatsMapper.map(from.overall),
        solo = modeStatsMapper.map(from.solo),
        duo = modeStatsMapper.map(from.duo),
        squad = modeStatsMapper.map(from.squad)
    )
}

class ModeStatsDtoMapper : Mapper<ModeStatsDto, ModeStats> {
    override fun map(from: ModeStatsDto) = ModeStats(
        score = from.score,
        wins = from.wins,
        kills = from.kills,
        kd = from.kd,
        matches = from.matches
    )
}