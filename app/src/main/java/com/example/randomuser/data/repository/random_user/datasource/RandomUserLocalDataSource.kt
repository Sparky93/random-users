package com.example.randomuser.data.repository.random_user.datasource

import com.example.randomuser.data.model.RandomUser
import io.reactivex.Flowable

class RandomUserLocalDataSourceImpl : RandomUserLocalDataSource {
    override fun getRandomUsers(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = Flowable.just(emptyList())

    override fun saveRandomUsers(randomUsers: List<RandomUser>) {

    }

    override fun clearAll() {

    }
}

interface RandomUserLocalDataSource {
    fun getRandomUsers(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>>

    fun saveRandomUsers(randomUsers: List<RandomUser>)
    fun clearAll()
}