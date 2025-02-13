package main.mappermodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import main.data.remote.profile.mapper.AccountDtoMapper
import main.data.remote.profile.mapper.BattlePassDtoMapper
import main.data.remote.profile.mapper.GameModeStatsDtoMapper
import main.data.remote.profile.mapper.ModeStatsDtoMapper
import main.data.remote.profile.mapper.ProfileDtoMapper
import main.data.remote.profile.mapper.StatsDtoMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideAccountDtoMapper(): AccountDtoMapper = AccountDtoMapper()

    @Provides
    @Singleton
    fun provideBattlePassDtoMapper(): BattlePassDtoMapper = BattlePassDtoMapper()

    @Provides
    @Singleton
    fun provideModeStatsDtoMapper(): ModeStatsDtoMapper = ModeStatsDtoMapper()

    @Provides
    @Singleton
    fun provideGameModeStatsMapper(
        modeStatsDtoMapper: ModeStatsDtoMapper
    ): GameModeStatsDtoMapper = GameModeStatsDtoMapper(modeStatsDtoMapper)

    @Provides
    @Singleton
    fun provideStatsDtoMapper(
        gameModeStatsMapper: GameModeStatsDtoMapper
    ): StatsDtoMapper = StatsDtoMapper(gameModeStatsMapper)

    @Provides
    @Singleton
    fun provideProfileDtoMapper(
        accountDtoMapper: AccountDtoMapper,
        battlePassDtoMapper: BattlePassDtoMapper,
        statsDtoMapper: StatsDtoMapper
    ): ProfileDtoMapper {
        return ProfileDtoMapper(accountDtoMapper, battlePassDtoMapper, statsDtoMapper)
    }
}