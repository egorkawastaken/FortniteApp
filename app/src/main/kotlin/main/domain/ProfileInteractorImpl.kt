package main.domain

import main.domain.profile.ProfileInteractor
import main.domain.profile.model.Profile
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor() : ProfileInteractor {

    override suspend fun loadData(accountId: String): Profile {
        return Profile("")
    }
}