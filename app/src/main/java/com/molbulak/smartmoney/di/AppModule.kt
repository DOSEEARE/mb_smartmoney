package com.molbulak.smartmoney.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.ui.loan.LoanViewModel
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.ui.notice.NoticeViewModel
import com.molbulak.smartmoney.ui.profile.ProfileViewModel
import com.molbulak.smartmoney.ui.support.SupportViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidApplication()) }
    viewModel { LoanViewModel(androidApplication()) }
    viewModel { NoticeViewModel(androidApplication()) }
    viewModel { SupportViewModel(androidApplication()) }
    viewModel { ProfileViewModel(androidApplication()) }
}


val navigationModule = module {
    single { Cicerone.create() }
    single { getRouter(get()) }
    factory { getNavigatorHolder(get()) }
    //  single { params -> LocalCiceroneHolder(params.get<ContainerType>() as ContainerType) }
}

fun getNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router