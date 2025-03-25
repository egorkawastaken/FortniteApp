package main.presentation.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import main.presentation.profile.mapper.AccountUiMapper
import main.presentation.profile.mapper.BattlePassUiMapper
import main.presentation.profile.mapper.GameModeStatsUiMapper
import main.presentation.profile.mapper.ModeStatsUiMapper
import main.presentation.profile.mapper.ProfileUiMapper
import main.presentation.profile.mapper.StatsUiMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileUiMapperModule {

    @Provides
    @Singleton
    fun provideAccountUiMapper() = AccountUiMapper()

    @Provides
    @Singleton
    fun provideBattlePassUiMapper() = BattlePassUiMapper()

    @Provides
    @Singleton
    fun provideModeStatsUiMapper() = ModeStatsUiMapper()

    @Provides
    @Singleton
    fun provideGameModeStatsMapper(
        modeStatsUiMapper: ModeStatsUiMapper
    ) = GameModeStatsUiMapper(modeStatsUiMapper)

    @Provides
    @Singleton
    fun provideStatsUiMapper(
        gameModeStatsMapper: GameModeStatsUiMapper
    ): StatsUiMapper = StatsUiMapper(gameModeStatsMapper)

    @Provides
    @Singleton
    fun provideProfileUiMapper(
        accountUiMapper: AccountUiMapper,
        battlePassUiMapper: BattlePassUiMapper,
        statsUiMapper: StatsUiMapper
    ): ProfileUiMapper = ProfileUiMapper(
        accountUiMapper = accountUiMapper,
        battlePassUiMapper = battlePassUiMapper,
        statsUiMapper = statsUiMapper
    )
}
