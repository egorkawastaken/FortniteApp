package main.data.remote.profile

import main.common.network.Response
import main.data.remote.profile.mapper.ProfileDtoMapper
import main.domain.profile.ProfileRepository
import main.domain.profile.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val profileDtoMapper: ProfileDtoMapper
) : ProfileRepository {

    override suspend fun loadData(accountId: String): Response<Profile> {
        return api.getAccountStats(accountId).map(profileDtoMapper)
    }
}