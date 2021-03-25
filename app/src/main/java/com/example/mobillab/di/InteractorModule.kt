package com.example.mobillab.di

import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.repo.DetailInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideCharacterInteractor() : CharacterInteractor {
        return CharacterInteractor()
    }

    @Provides
    @Singleton
    fun provideDetailInteractor() : DetailInteractor   {
        return DetailInteractor()
    }
}