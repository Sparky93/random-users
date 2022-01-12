package com.example.randomuser.presentation.di.core

import com.example.randomuser.data.repository.random_user.datasource.RandomUserLocalDataSource
import com.example.randomuser.data.repository.random_user.datasource.RandomUserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun provideRandomUserLocalDataSource(): RandomUserLocalDataSource =
        RandomUserLocalDataSourceImpl()
}