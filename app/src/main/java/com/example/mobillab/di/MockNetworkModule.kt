package com.example.mobillab.di

import com.example.mobillab.repo.network.CharacterAPI
import com.example.mobillab.repo.network.CharacterMockService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockNetworkModule {

    @Singleton
    @Provides
    fun provideMockCharacterApi(): CharacterAPI = CharacterMockService()
}