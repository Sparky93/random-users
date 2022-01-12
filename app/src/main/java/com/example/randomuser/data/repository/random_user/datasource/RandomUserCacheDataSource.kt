package com.example.randomuser.data.repository.random_user.datasource

import com.example.randomuser.data.model.RandomUser
import io.reactivex.Flowable

class RandomUserCacheDataSourceImpl : RandomUserCacheDataSource {
    private val randomUsersList: MutableList<RandomUser> by lazy { mutableListOf() }

    override fun getRandomUsers(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = Flowable.just(randomUsersList)

    override fun saveRandomUsers(randomUsers: List<RandomUser>): Boolean {
        randomUsersList.clear()

        return randomUsersList.addAll(randomUsers)
    }
}

interface RandomUserCacheDataSource {
    fun getRandomUsers(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>>

    fun saveRandomUsers(randomUsers: List<RandomUser>): Boolean
}