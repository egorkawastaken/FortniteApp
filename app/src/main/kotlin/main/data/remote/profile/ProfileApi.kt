package main.data.remote.profile

import main.common.network.Response
import main.data.remote.profile.model.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {
    @GET("stats/br/v2")
    suspend fun getAccountStats(
        @Query("name")
        accountId: String
    ): Response<ProfileDto>
}