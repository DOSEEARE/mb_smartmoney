package com.molbulak.smartmoney

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.service.network.response.news.News
import com.molbulak.smartmoney.service.network.response.notice.Notice
import com.molbulak.smartmoney.ui.loan.LoanFragment
import com.molbulak.smartmoney.ui.loan.NewsDetailFragment
import com.molbulak.smartmoney.ui.login.LoginBaseActivity
import com.molbulak.smartmoney.ui.login.auth.AuthFragment
import com.molbulak.smartmoney.ui.login.check_number.CheckNumberFragment
import com.molbulak.smartmoney.ui.login.login.LoginFragment
import com.molbulak.smartmoney.ui.login.restore.RestoreFragment
import com.molbulak.smartmoney.ui.more.MoreFragment
import com.molbulak.smartmoney.ui.notification.NoticeDetailFragment
import com.molbulak.smartmoney.ui.notification.NoticeFragment
import com.molbulak.smartmoney.ui.profile.ProfileFragment
import com.molbulak.smartmoney.ui.support.SupportFragment

object Screens {
    fun LoginHostScreen() = ActivityScreen {
        Intent(it, LoginBaseActivity::class.java)
    }

    fun MainScreen() = ActivityScreen {
        Intent(it, MainHostActivity::class.java)
    }

    fun NewsDetailScreen(news : News) = FragmentScreen {
        NewsDetailFragment(news)
    }

    fun NoticeDetailScreen(notice : Notice) = FragmentScreen {
        NoticeDetailFragment(notice)
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
        NoticeFragment()
    }

    fun MoreScreen() = FragmentScreen {
        MoreFragment()
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