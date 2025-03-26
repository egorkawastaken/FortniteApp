package main.domain.profile

import main.domain.profile.model.Profile

interface ProfileInteractor {
    suspend fun loadData(accountId: String): Profile
}