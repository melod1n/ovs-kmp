package dev.meloda.overseerr.common

import android.app.Application
import dev.meloda.overseerr.di.appModule
import org.koin.core.context.startKoin

class AppGlobal : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}
