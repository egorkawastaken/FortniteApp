package main.presentation.profile.mapper

import main.common.utils.Mapper
import main.domain.profile.model.Profile
import main.presentation.profile.model.ProfileUiModel
import javax.inject.Inject

class ProfileUiMapper @Inject constructor(
    private val accountUiMapper: AccountUiMapper,
    private val battlePassUiMapper: BattlePassUiMapper,
    private val statsUiMapper: StatsUiMapper
) : Mapper<Profile, ProfileUiModel> {
    override fun map(from: Profile): ProfileUiModel {
        return ProfileUiModel(
            account = accountUiMapper.map(from.account),
            battlePass = battlePassUiMapper.map(from.battlePass),
            stats = statsUiMapper.map(from.stats)
        )
    }
}