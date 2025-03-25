package main.presentation.profile.mapper

import main.common.utils.Mapper
import main.domain.profile.model.BattlePass
import main.presentation.profile.model.BattlePassUiModel

class BattlePassUiMapper : Mapper<BattlePass, BattlePassUiModel> {
    override fun map(from: BattlePass): BattlePassUiModel {
        return BattlePassUiModel(
            level = from.level,
            progress = from.progress
        )
    }
}