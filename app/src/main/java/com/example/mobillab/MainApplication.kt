package com.example.mobillab

import android.R.id
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.mobillab.di.ApplicationModule
import com.example.mobillab.di.CharactersComponent
import com.example.mobillab.di.DaggerCharactersComponent
import com.google.firebase.analytics.FirebaseAnalytics
import java.time.LocalDateTime


class MainApplication: Application() {

    lateinit var injector: CharactersComponent
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        injector = DaggerCharactersComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val loginInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bundleOf("user" to "test1231e","date" to LocalDateTime.now())
        } else {
            bundleOf("user" to "test1231e")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, loginInfo)
    }
}