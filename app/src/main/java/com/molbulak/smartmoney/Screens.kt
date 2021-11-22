package com.molbulak.smartmoney

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.ui.login.login.LoginFragment
import com.molbulak.smartmoney.ui.login.LoginHostActivity
import com.molbulak.smartmoney.ui.login.auth.AuthFragment
import com.molbulak.smartmoney.ui.login.check_number.CheckNumberFragment
import com.molbulak.smartmoney.ui.login.restore.RestoreFragment

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

    fun CheckNumberScreen() = FragmentScreen {
        CheckNumberFragment()
    }

    fun AuthScreen(country: Country, numberPhone: String) = FragmentScreen {
        AuthFragment(country, numberPhone)
    }

    fun RestoreScreen() = FragmentScreen {
        RestoreFragment()
    }

}