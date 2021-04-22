package com.example.mobillab.di

import com.example.mobillab.repo.database.CharacterDAO
import com.example.mobillab.repo.database.MockCharacterDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockDatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase (): CharacterDAO = MockCharacterDAO()
}