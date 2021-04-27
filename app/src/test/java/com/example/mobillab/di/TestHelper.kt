package com.example.mobillab.di

import com.example.mobillab.MainApplication
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowLog

val testInjector: TestComponent
    get() {
        ShadowLog.stream = System.out
        val application = RuntimeEnvironment.application as MainApplication
        val component = DaggerTestComponent.builder().applicationModule(ApplicationModule(application.applicationContext)).build()
        application.injector = component
        return component
    }
