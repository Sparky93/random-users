package com.example.randomuser.data.repository.random_user.datasource

import com.example.randomuser.data.api.RandomUserApi
import com.example.randomuser.data.api.response.GetRandomUsersResponse
import io.reactivex.Single

class RandomUserRemoteDataSourceImpl(
    private val api: RandomUserApi
) : RandomUserRemoteDataSource {
    override fun getRandomUsers(page: Int, results: Int, seed: String): Single<GetRandomUsersResponse> =
        api.getUsers(page, results, seed)
}

interface RandomUserRemoteDataSource {
    fun getRandomUsers(page: Int, results: Int, seed: String): Single<GetRandomUsersResponse>
}