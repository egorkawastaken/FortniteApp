package main.data.remote.profile

import main.data.remote.profile.model.ProfileDto
import main.domain.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : ProfileRepository {

    override suspend fun loadData(accountId: String): ProfileDto {
        api.getAccountStats(accountId)
        return ProfileDto()
    }
}