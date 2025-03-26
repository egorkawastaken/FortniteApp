package main.domain

import main.domain.profile.ProfileInteractor
import main.domain.profile.ProfileRepository
import main.domain.profile.model.Profile
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    private val repository: ProfileRepository
) : ProfileInteractor {

    override suspend fun loadData(accountId: String): Profile {
        return repository.loadData(accountId).requireData()
    }
}