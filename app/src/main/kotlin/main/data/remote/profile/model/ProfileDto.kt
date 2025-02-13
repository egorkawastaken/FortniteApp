package main.data.remote.profile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("account")
    val accountDto: AccountDto,

    @SerializedName("battle_pass")
    val battlePassDto: BattlePassDto,

    @SerializedName("player_stats")
    val statsDto: StatsDto
)

data class AccountDto(
    @SerializedName("id")
    val id: String,

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
    val all: GameModeStatsDto
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
    val squad: ModeStatsDto
)

data class ModeStatsDto(
    @Expose
    @SerializedName("score")
    val score: Int,

    @Expose
    @SerializedName("wins")
    val wins: Int,

    @Expose
    @SerializedName("kills")
    val kills: Int,

    @Expose
    @SerializedName("kd")
    val kd: Double,

    @Expose
    @SerializedName("matches")
    val matches: Int
)