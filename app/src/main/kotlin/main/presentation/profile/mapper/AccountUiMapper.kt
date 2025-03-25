package main.presentation.profile.mapper

import main.common.utils.Mapper
import main.domain.profile.model.Account
import main.presentation.profile.model.AccountUiModel

class AccountUiMapper : Mapper<Account, AccountUiModel> {
    override fun map(from: Account): AccountUiModel {
        return AccountUiModel(
            id = from.id,
            name = from.name
        )
    }
}