package com.example.randomuser.data.api

import com.example.randomuser.data.api.response.GetRandomUsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api")
    fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ): Single<GetRandomUsersResponse>
}