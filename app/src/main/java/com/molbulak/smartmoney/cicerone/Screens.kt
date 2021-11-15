package com.molbulak.smartmoney.cicerone

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.MainHostActivity
import com.molbulak.smartmoney.ui.login.AuthFragment
import com.molbulak.smartmoney.ui.login.LoginFragment
import com.molbulak.smartmoney.ui.login.LoginHostActivity

object Screens {
    fun LoginHostScreen() = ActivityScreen {
        Intent(it, LoginHostActivity::class.java)
    }

    fun MainScreen() = ActivityScreen {
        Intent(it, MainHostActivity::class.java)
    }

    fun LoginScreen() = FragmentScreen {
        LoginFragment()
    }

    fun AuthScreen() = FragmentScreen{
        AuthFragment()
    }

}