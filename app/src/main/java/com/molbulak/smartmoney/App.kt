package com.molbulak.smartmoney

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

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
        INSTANCE = this
        cicerone = Cicerone.create()
    }

    fun getNavigator(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }


}