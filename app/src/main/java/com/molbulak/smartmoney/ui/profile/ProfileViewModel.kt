package com.molbulak.smartmoney.ui.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.molbulak.smartmoney.base.BaseViewModel

class ProfileViewModel(application: Application) : BaseViewModel(application) {

val selectedScreen = MutableLiveData<Boolean>()


}