package main.domain.profile

import main.common.utils.Response
import main.domain.profile.model.Profile

interface ProfileRepository {
    suspend fun loadData(accountId: String): Response<Profile>
}