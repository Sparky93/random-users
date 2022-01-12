package com.example.randomuser.presentation.di.core

import com.example.randomuser.data.repository.random_user.datasource.RandomUserCacheDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideRandomUserCacheDataSource(): RandomUserCacheDataSource =
        RandomUserCacheDataSourceImpl()
}