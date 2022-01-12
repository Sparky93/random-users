package com.example.randomuser.domain.usecase.random_user

import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.data.repository.random_user.RandomUserRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetRandomUsersUsecase(private val repository: RandomUserRepository) {
    fun execute(
        page: Int,
        results: Int,
        seed: String
    ): Flowable<List<RandomUser>> = repository
        .getRandomUsers(page, results, seed)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}