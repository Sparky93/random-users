package com.example.randomuser.data.api

import com.example.randomuser.data.api.response.GetRandomUsersResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RandomUserService {
    private const val BASE_URL = "https://randomuser.me/"

    interface RandomUserApi {
        @GET("api")
        fun getUsers(
            @Query("page") page: Int,
            @Query("results") results: Int,
            @Query("seed") seed: String
        ): Single<GetRandomUsersResponse>
    }

    fun getApi(): RandomUserApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RandomUserApi::class.java)
}