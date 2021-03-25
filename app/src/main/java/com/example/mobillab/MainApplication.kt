package com.example.mobillab

import android.app.Application
import com.example.mobillab.di.ApplicationModule
import com.example.mobillab.di.CharactersComponent
import com.example.mobillab.di.DaggerCharactersComponent
import com.example.mobillab.di.InteractorModule

class MainApplication: Application() {

    lateinit var injector: CharactersComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerCharactersComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}