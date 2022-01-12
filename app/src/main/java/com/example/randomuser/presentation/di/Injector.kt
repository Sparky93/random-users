package com.example.randomuser.presentation.di

import com.example.randomuser.presentation.di.list.ListSubComponent

interface Injector {
    fun createListSubComponent(): ListSubComponent
}