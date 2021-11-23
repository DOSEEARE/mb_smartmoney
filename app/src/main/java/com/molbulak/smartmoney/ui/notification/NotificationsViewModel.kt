package com.molbulak.smartmoney.ui.notification

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molbulak.smartmoney.base.BaseViewModel

class NotificationsViewModel (application: Application) : BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}