package com.example.mobillab.di

import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.repo.DetailInteractor
import com.example.mobillab.repo.network.CharacterAPI
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideCharacterInteractor(service : CharacterAPI) : CharacterInteractor = CharacterInteractor(service)

    @Provides
    @Singleton
    fun provideDetailInteractor(service : CharacterAPI) : DetailInteractor = DetailInteractor(service)
}