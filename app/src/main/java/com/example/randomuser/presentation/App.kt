package com.example.randomuser.presentation

import android.app.Application
import com.example.randomuser.BuildConfig
import com.example.randomuser.presentation.di.Injector
import com.example.randomuser.presentation.di.core.*
import com.example.randomuser.presentation.di.list.ListSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule())
            .localDataModule(LocalDataModule())
            .cacheDataModule(CacheDataModule())
            .repositoryModule(RepositoryModule())
            .usecaseModule(UsecaseModule())
            .build()
    }

    override fun createListSubComponent(): ListSubComponent =
        appComponent.listSubComponent().create()
}