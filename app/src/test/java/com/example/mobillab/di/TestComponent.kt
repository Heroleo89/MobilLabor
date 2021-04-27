package com.example.mobillab.di

import com.example.mobillab.DatabaseTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Singleton

@Singleton
@Component(modules=[InteractorModule::class, ApplicationModule::class, MockNetworkModule::class, MockDatabaseModule::class])
interface TestComponent : CharactersComponent{
    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun inject(databaseTest: DatabaseTest)
}
