package com.molbulak.smartmoney

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.di.viewModelModule
import com.molbulak.smartmoney.service.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var INSTANCE: App

        fun getRouter(): Router {
            return INSTANCE.cicerone.router
        }
    }

    private lateinit var cicerone: Cicerone<Router>
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        INSTANCE = this
        cicerone = Cicerone.create()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
    }

    fun getNavigator(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

}