package com.molbulak.smartmoney

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.ui.ContainerFragment
import com.molbulak.smartmoney.ui.loan.LoanFragment
import com.molbulak.smartmoney.ui.loan.NewsDetailFragment
import com.molbulak.smartmoney.ui.login.LoginHostActivity
import com.molbulak.smartmoney.ui.login.auth.AuthFragment
import com.molbulak.smartmoney.ui.login.check_number.CheckNumberFragment
import com.molbulak.smartmoney.ui.login.login.LoginFragment
import com.molbulak.smartmoney.ui.login.restore.RestoreFragment
import com.molbulak.smartmoney.ui.more.MoreFragment
import com.molbulak.smartmoney.ui.notification.NotificationFragment
import com.molbulak.smartmoney.ui.profile.ProfileFragment
import com.molbulak.smartmoney.ui.support.SupportFragment
import com.molbulak.smartmoney.util.enums.FragmentType

object Screens {
    fun LoginHostScreen() = ActivityScreen {
        Intent(it, LoginHostActivity::class.java)
    }

    fun MainScreen() = ActivityScreen {
        Intent(it, MainHostActivity::class.java)
    }

    fun NewsDetailScreen() = FragmentScreen {
        NewsDetailFragment()
    }

    fun LoginScreen() = FragmentScreen {
        LoginFragment()
    }

    fun LoanScreen() = FragmentScreen {
        LoanFragment()
    }

    fun ProfileScreen() = FragmentScreen {
        ProfileFragment()
    }

    fun SupportScreen() = FragmentScreen {
        SupportFragment()
    }

    fun NotificationScreen() = FragmentScreen {
        NotificationFragment()
    }

    fun MoreScreen() = FragmentScreen {
        MoreFragment()
    }

    fun ContainerScreen(fragmentType: FragmentType) = FragmentScreen {
        ContainerFragment(fragmentType)
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