package com.example.mobillab.di

import com.example.mobillab.ui.characters.CharactersFragment
import com.example.mobillab.ui.details.DetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[InteractorModule::class,ApplicationModule::class, NetworkModule::class])
interface CharactersComponent{
    fun inject(charactersFragment: CharactersFragment)
    fun inject(detailsFragment: DetailsFragment)
}

