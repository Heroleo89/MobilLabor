package com.example.mobillab.di

import android.content.Context
import com.example.mobillab.repo.database.CharacterDAO
import com.example.mobillab.repo.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase (context: Context): CharacterDAO = CharacterDatabase.getInstance(context).characterDao()
}