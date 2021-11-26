package com.molbulak.smartmoney.base

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    abstract val navigator: AppNavigator
    private val navigatorHolder: NavigatorHolder by inject()

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
