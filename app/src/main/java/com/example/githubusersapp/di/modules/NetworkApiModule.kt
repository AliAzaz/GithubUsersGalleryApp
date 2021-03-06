package com.example.githubusersapp.di.modules

import com.example.githubusersapp.di.auth.AuthApi
import com.example.githubusersapp.di.auth.remote.ApiResponseCallAdapterFactory
import com.example.githubusersapp.utils.CONSTANTS.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * @author AliAzazAlam on 2/13/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Provides
    fun buildBackendApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun buildRetrofitClient(
        okHttpClient: OkHttpClient,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }

    @Provides
    fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().also { item ->
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            item.addInterceptor(log)
            item.retryOnConnectionFailure(true)
        }.build()
    }


    @Provides
    fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun getCoroutineCallAdapter(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory.invoke()
    }

}