package com.molbulak.smartmoney.ui.support

import android.app.Application
import com.molbulak.smartmoney.base.BaseViewModel

class SupportViewModel(application: Application) : BaseViewModel(application) {

    val listFaq = network.listFaq()

}