package com.example.randomuser.presentation.di.list

import com.example.randomuser.presentation.list.ListFragment
import dagger.Subcomponent

@ListScope
@Subcomponent(modules = [ListModule::class])
interface ListSubComponent {
    fun inject(listFragment: ListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListSubComponent
    }
}