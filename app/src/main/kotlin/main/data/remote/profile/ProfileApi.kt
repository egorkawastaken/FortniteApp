package main.data.remote.profile

import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("stats/br/v2/{accountId}")
    fun getAccountStats(
        @Path("accountId") accountId: String
    )
}