package com.molbulak.smartmoney.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.cicerone.LocalCiceroneHolder
import com.molbulak.smartmoney.ui.loan.LoanViewModel
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.ui.notification.NotificationsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidApplication()) }
    viewModel { LoanViewModel(androidApplication()) }
    viewModel { NotificationsViewModel(androidApplication()) }
}


val navigationModule = module {
    single { Cicerone.create() }
    single { getRouter(get()) }
    factory { getNavigatorHolder(get()) }
    single { LocalCiceroneHolder() }
}

fun getNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router
fun getCicerone(): Cicerone<Router> {
    return Cicerone.create()
}