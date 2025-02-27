package main.data.remote.profile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("account")
    val accountDto: AccountDto,

    @SerialName("battlePass")
    val battlePassDto: BattlePassDto,

    @SerialName("stats")
    val statsDto: StatsDto
)

@Serializable
data class AccountDto(
    val id: String,
    val name: String
)

@Serializable
data class BattlePassDto(
    val level: Int,
    val progress: Int
)

@Serializable
data class StatsDto(

    @SerialName("all")
    val allDto: GameModeStatsDto,

    @SerialName("keyboardMouse")
    val keyboardMouseDto: GameModeStatsDto? = null,

    @SerialName("gamepad")
    val gamepadDto: GameModeStatsDto? = null,

    @SerialName("touch")
    val touchDto: GameModeStatsDto? = null
)

@Serializable
data class GameModeStatsDto(

    @SerialName("overall")
    val overallDto: ModeStatsDto,

    @SerialName("solo")
    val soloDto: ModeStatsDto? = null,

    @SerialName("duo")
    val duoDto: ModeStatsDto? = null,

    @SerialName("squad")
    val squadDto: ModeStatsDto? = null,

    @SerialName("ltm")
    val ltmDto: ModeStatsDto? = null
)

@Serializable
data class ModeStatsDto(
    val score: Int,
    val scorePerMin: Double,
    val scorePerMatch: Double,
    val wins: Int,
    val top3: Int? = null,
    val top5: Int? = null,
    val top6: Int? = null,
    val top10: Int? = null,
    val top12: Int? = null,
    val top25: Int? = null,
    val kills: Int,
    val killsPerMin: Double,
    val killsPerMatch: Double,
    val deaths: Int,
    val kd: Double,
    val matches: Int,
    val winRate: Double,
    val minutesPlayed: Int,
    val playersOutlived: Int,
    val lastModified: String
)