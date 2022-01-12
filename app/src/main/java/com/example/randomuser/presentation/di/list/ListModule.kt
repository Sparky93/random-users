package com.example.randomuser.presentation.di.list

import com.example.randomuser.domain.usecase.random_user.GetRandomUsersUsecase
import com.example.randomuser.presentation.list.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListModule {
    @Provides
    @ListScope
    fun provideListViewModelFactory(
        getRandomUsersUsecase: GetRandomUsersUsecase
    ): ListViewModelFactory = ListViewModelFactory(getRandomUsersUsecase)
}