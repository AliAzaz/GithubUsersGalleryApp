package com.example.githubusersapp.di.modules

import com.example.githubusersapp.base.repository.GeneralDataSource
import com.example.githubusersapp.base.repository.GeneralRepository
import com.example.githubusersapp.di.auth.AuthApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralRepositoryModule {

    @Singleton
    @Provides
    fun provideGeneralDataSource(authApi: AuthApi): GeneralDataSource {
        return GeneralRepository(authApi)
    }

}