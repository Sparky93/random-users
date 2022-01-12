package com.example.randomuser.data.repository.random_user

import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.data.repository.random_user.datasource.RandomUserCacheDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserLocalDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserRemoteDataSource
import io.reactivex.Flowable

class RandomUserRepositoryImpl(
    private val remoteDataSource: RandomUserRemoteDataSource,
    private val cacheDataSource: RandomUserCacheDataSource,
    private val localDataSource: RandomUserLocalDataSource
) : RandomUserRepository {

    override fun getRandomUsers(page: Int, results: Int, seed: String): Flowable<List<RandomUser>> =
        Flowable.concatArrayEager(
            getRandomUsersFromCache(page, results, seed),
            getRandomUsersFromLocal(page, results, seed),
            getRandomUsersFromRemote(page, results, seed)
        )
    // using concatArrayEager() operator instead of merge() operator due to the fact that the first
    // one is consistent in emissions order. e.g: if the db it's slower than the network for some reason,
    // the older data would be saved instead of the newer data

    private fun getRandomUsersFromCache(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = cacheDataSource.getRandomUsers(page, results, seed)

    private fun getRandomUsersFromLocal(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = localDataSource
        .getRandomUsers(page, results, seed)
        .doAfterNext { cacheDataSource.saveRandomUsers(it) }

    private fun getRandomUsersFromRemote(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = remoteDataSource
        .getRandomUsers(page, results, seed)
        .map { it.results }
        .doAfterSuccess {
            cacheDataSource.saveRandomUsers(it)
            localDataSource.saveRandomUsers(it)
        }.toFlowable()
}

interface RandomUserRepository {
    fun getRandomUsers(page: Int, results: Int, seed: String): Flowable<List<RandomUser>>
}
