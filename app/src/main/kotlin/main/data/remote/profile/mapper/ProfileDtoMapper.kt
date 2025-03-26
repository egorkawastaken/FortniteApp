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
            account = account,
            battlePass = battlePass,
            stats = stats
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
        all = gameModeStatsMapper.map(from.allDto),
        keyboardMouse = gameModeStatsMapper.map(from.keyboardMouseDto),
        gamepad = gameModeStatsMapper.map(from.gamepadDto),
        touch = gameModeStatsMapper.map(from.touchDto),
    )
}

class GameModeStatsDtoMapper @Inject constructor(
    private val modeStatsMapper: ModeStatsDtoMapper
) : Mapper<GameModeStatsDto?, GameModeStats> {
    override fun map(from: GameModeStatsDto?) = GameModeStats(
        overall = modeStatsMapper.map(from?.overallDto),
        solo = modeStatsMapper.map(from?.soloDto),
        duo = modeStatsMapper.map(from?.duoDto),
        squad = modeStatsMapper.map(from?.squadDto),
        ltm = modeStatsMapper.map(from?.ltmDto)
    )
}

class ModeStatsDtoMapper : Mapper<ModeStatsDto?, ModeStats> {
    override fun map(from: ModeStatsDto?) = ModeStats(
        score = from?.score,
        scorePerMin = from?.scorePerMin,
        scorePerMatch = from?.scorePerMatch,
        wins = from?.wins,
        top3 = from?.top3,
        top5 = from?.top5,
        top6 = from?.top6,
        top10 = from?.top10,
        top12 = from?.top12,
        top25 = from?.top25,
        kills = from?.kills,
        killsPerMin = from?.killsPerMin,
        killsPerMatch = from?.killsPerMatch,
        deaths = from?.deaths,
        kd = from?.kd,
        matches = from?.matches,
        winRate = from?.winRate,
        minutesPlayed = from?.minutesPlayed,
        playersOutlived = from?.playersOutlived,
        lastModified = from?.lastModified
    )
}