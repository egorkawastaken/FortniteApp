package main.data.remote.profile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @Expose
    @SerializedName("account")
    val accountDto: AccountDto,

    @Expose
    @SerializedName("battlePass")
    val battlePassDto: BattlePassDto,

    @Expose
    @SerializedName("stats")
    val statsDto: StatsDto
)

data class AccountDto(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String
)

data class BattlePassDto(
    @Expose
    @SerializedName("level")
    val level: Int,

    @Expose
    @SerializedName("progress")
    val progress: Int
)

data class StatsDto(
    @Expose
    @SerializedName("all")
    val all: GameModeStatsDto,

    @Expose
    @SerializedName("keyboardMouse")
    val keyboardMouse: GameModeStatsDto,

    @Expose
    @SerializedName("gamepad")
    val gamepad: GameModeStatsDto,

    @Expose
    @SerializedName("touch")
    val touch: GameModeStatsDto
)

data class GameModeStatsDto(
    @Expose
    @SerializedName("overall")
    val overall: ModeStatsDto,

    @Expose
    @SerializedName("solo")
    val solo: ModeStatsDto,

    @Expose
    @SerializedName("duo")
    val duo: ModeStatsDto,

    @Expose
    @SerializedName("squad")
    val squad: ModeStatsDto,

    @Expose
    @SerializedName("ltm")
    val ltm: ModeStatsDto
)

data class ModeStatsDto(
    @Expose
    @SerializedName("score")
    val score: Int,

    @Expose
    @SerializedName("scorePerMin")
    val scorePerMin: Double,

    @Expose
    @SerializedName("scorePerMatch")
    val scorePerMatch: Double,

    @Expose
    @SerializedName("wins")
    val wins: Int,

    @Expose
    @SerializedName("top3")
    val top3: Int,

    @Expose
    @SerializedName("top5")
    val top5: Int,

    @Expose
    @SerializedName("top6")
    val top6: Int,

    @Expose
    @SerializedName("top10")
    val top10: Int,

    @Expose
    @SerializedName("top12")
    val top12: Int,

    @Expose
    @SerializedName("top25")
    val top25: Int,

    @Expose
    @SerializedName("kills")
    val kills: Int,

    @Expose
    @SerializedName("killsPerMin")
    val killsPerMin: Double,

    @Expose
    @SerializedName("killsPerMatch")
    val killsPerMatch: Double,

    @Expose
    @SerializedName("deaths")
    val deaths: Int,

    @Expose
    @SerializedName("kd")
    val kd: Double,

    @Expose
    @SerializedName("matches")
    val matches: Int,

    @Expose
    @SerializedName("winRate")
    val winRate: Double,

    @Expose
    @SerializedName("minutesPlayed")
    val minutesPlayed: Int,

    @Expose
    @SerializedName("playersOutlived")
    val playersOutlived: Int,

    @Expose
    @SerializedName("lastModified")
    val lastModified: String
)