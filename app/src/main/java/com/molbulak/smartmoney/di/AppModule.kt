package com.molbulak.smartmoney.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.ui.loan.LoanViewModel
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.ui.notification.NotificationsViewModel
import okhttp3.Route
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidApplication()) }
    viewModel { LoanViewModel(androidApplication()) }
    viewModel { NotificationsViewModel(androidApplication()) }
}

/*
val navigationModule = module {
        factory { get { Cicerone<Router>().c } }
}*/
