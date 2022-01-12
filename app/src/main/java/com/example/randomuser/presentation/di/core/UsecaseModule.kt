package com.example.randomuser.presentation.di.core

import com.example.randomuser.data.repository.random_user.RandomUserRepository
import com.example.randomuser.domain.usecase.random_user.GetRandomUsersUsecase
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {
    @Provides
    fun provideGetRandomUsersUsecase(repository: RandomUserRepository) =
        GetRandomUsersUsecase(repository)
}