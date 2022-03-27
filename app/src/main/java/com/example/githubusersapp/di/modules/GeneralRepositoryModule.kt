package com.example.githubusersapp.di.modules

import com.example.githubusersapp.base.repository.GeneralDataSource
import com.example.githubusersapp.base.repository.GeneralRepository
import com.example.githubusersapp.di.auth.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GeneralRepositoryModule {

    @Provides
    fun provideGeneralDataSource(authApi: AuthApi): GeneralDataSource {
        return GeneralRepository(authApi)
    }

}