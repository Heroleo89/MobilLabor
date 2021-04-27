package com.example.mobillab.di

import com.example.mobillab.DatabaseTest
import com.example.mobillab.InteractorTest
import com.example.mobillab.ServiceTest
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
    fun inject(serviceTest: ServiceTest)
    fun inject(interactorTest: InteractorTest)
}
