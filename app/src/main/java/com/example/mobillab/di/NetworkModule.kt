package com.example.mobillab.di

import com.example.mobillab.repo.network.CharacterAPI
import com.example.mobillab.repo.network.CharacterInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = CharacterInterceptor()
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Singleton
    @Provides
    fun provideCharacterApi(client: OkHttpClient): CharacterAPI {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(CharacterAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(CharacterAPI::class.java)
    }

/*
    @Singleton
    @Provides
    fun provideCharacterMockApi(client: OkHttpClient): CharacterAPI {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(CharacterAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(CharacterAPI::class.java)
    }*/


}