package com.mcmouse88.harrypotter.data.di

import android.app.Application
import android.content.Context

class HarryPotterApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is HarryPotterApp -> appComponent
        else -> applicationContext.appComponent
    }