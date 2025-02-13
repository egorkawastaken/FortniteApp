package main.data.remote.profile

import main.common.utils.Response
import main.data.remote.profile.model.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("stats/br/v2/{accountId}")
    suspend fun getAccountStats(
        @Path("accountId") accountId: String
    ): Response<ProfileDto>
}