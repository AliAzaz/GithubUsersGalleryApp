package com.example.githubusersapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.githubusersapp.base.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}