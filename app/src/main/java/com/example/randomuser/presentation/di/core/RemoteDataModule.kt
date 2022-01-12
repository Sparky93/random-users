package com.example.randomuser.presentation.di.core

import com.example.randomuser.data.api.RandomUserApi
import com.example.randomuser.data.repository.random_user.datasource.RandomUserRemoteDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRandomUserRemoteDataSource(api: RandomUserApi): RandomUserRemoteDataSource =
        RandomUserRemoteDataSourceImpl(api)
}