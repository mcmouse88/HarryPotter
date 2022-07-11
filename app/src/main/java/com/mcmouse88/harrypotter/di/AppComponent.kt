package com.mcmouse88.harrypotter.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppComponent : Application() {

    /**
     * [androidLogger] используется для логирования ошибок, и вв нем описывается насколько подробно
     * нужно выводить информацию об ошибках. [androidContext] используется для того, чтобы у
     * Koin был доступ к контексту.
     */
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppComponent)
            modules(listOf(appModule, domainModel, dataModule))
        }
    }
}