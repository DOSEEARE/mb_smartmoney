package com.molbulak.smartmoney

import android.app.Application
import com.molbulak.smartmoney.cicerone.LocalCiceroneHolder
import com.molbulak.smartmoney.di.navigationModule
import com.molbulak.smartmoney.di.viewModelModule
import com.molbulak.smartmoney.service.preference.AppPreferences
import com.molbulak.smartmoney.service.preference.EncryptedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var localCicerone: LocalCiceroneHolder
    }

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        EncryptedPreferences.init(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, navigationModule))
        }
        localCicerone = LocalCiceroneHolder
    }

}