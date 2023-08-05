package org.dgeek.currencyexchange.android

import android.app.Application
import org.dgeek.currencyexchange.framework.di.getModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrencyApp)
            modules(getModuleList())
        }
    }
}