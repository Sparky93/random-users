package com.example.randomuser.presentation.di.core

import com.example.randomuser.presentation.di.list.ListSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CacheDataModule::class,
        LocalDataModule::class,
        RemoteDataModule::class,
        UsecaseModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun listSubComponent(): ListSubComponent.Factory
}