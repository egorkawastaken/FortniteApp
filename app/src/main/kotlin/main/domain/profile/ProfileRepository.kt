package main.domain.profile

import main.data.remote.profile.model.ProfileDto

interface ProfileRepository {
    suspend fun loadData(accountId: String): ProfileDto
}