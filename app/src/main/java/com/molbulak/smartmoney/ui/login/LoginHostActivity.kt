package com.molbulak.smartmoney.ui.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.ActivityAuthHostBinding
import com.molbulak.smartmoney.databinding.AlertSuccessBinding
import com.molbulak.smartmoney.service.AppPreferences
import com.molbulak.smartmoney.util.ClickListener

class LoginHostActivity : AppCompatActivity() {
    private val navigator = object : AppNavigator(this, R.id.auth_container) {

        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment,
        ) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_enter_next,
                R.anim.slide_leave_next,
                R.anim.slide_enter_back,
                R.anim.slide_leave_back
            )
        }

    }
    private lateinit var binding: ActivityAuthHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (AppPreferences.isLogined) {
            App.getRouter().newRootScreen(Screens.MainScreen())
        } else {
            if (savedInstanceState == null && !AppPreferences.isLogined) {
                navigator.applyCommands(arrayOf<Command>(Replace(Screens.LoginScreen())))
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.getNavigator().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.getNavigator().removeNavigator()
    }

    fun showSuccess(
        titleText: String,
        contentText: String,
        btnText: String,
        listener: ClickListener,
    ) {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false)
        val binding: AlertSuccessBinding = AlertSuccessBinding.inflate(LayoutInflater.from(this))
        //<---------set view under of this------------->
        binding.contentTv.text = contentText
        binding.titleTv.text = titleText
        binding.acceptBtn.text = btnText
        binding.acceptBtn.setOnClickListener { listener.btnClicked(dialog) }
        //<---------set view top of this------------->
        dialog.setContentView(binding.root)
        dialog.show()
    }
}