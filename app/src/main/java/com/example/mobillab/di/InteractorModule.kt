package com.example.mobillab.di

import com.example.mobillab.repo.CharacterInteractor
import com.example.mobillab.repo.DetailInteractor
import com.example.mobillab.repo.database.CharacterDAO
import com.example.mobillab.repo.network.CharacterAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideCharacterInteractor(service : CharacterAPI, database : CharacterDAO) : CharacterInteractor = CharacterInteractor(service,database)

    @Provides
    @Singleton
    fun provideDetailInteractor(service : CharacterAPI, database : CharacterDAO) : DetailInteractor = DetailInteractor(service,database)
}