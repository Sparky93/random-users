package com.example.randomuser.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomuser.domain.usecase.random_user.GetRandomUsersUsecase

class ListViewModelFactory(
    private val getRandomUsersUsecase: GetRandomUsersUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ListViewModel(getRandomUsersUsecase) as T
}