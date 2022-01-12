package com.example.randomuser.presentation.di.core

import com.example.randomuser.data.repository.random_user.RandomUserRepository
import com.example.randomuser.data.repository.random_user.RandomUserRepositoryImpl
import com.example.randomuser.data.repository.random_user.datasource.RandomUserCacheDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserLocalDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRandomUserRepository(
        randomUserCacheDataSource: RandomUserCacheDataSource,
        randomUserLocalDataSource: RandomUserLocalDataSource,
        randomUserRemoteDataSource: RandomUserRemoteDataSource
    ): RandomUserRepository = RandomUserRepositoryImpl(
        randomUserRemoteDataSource,
        randomUserCacheDataSource,
        randomUserLocalDataSource
    )
}