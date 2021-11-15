package com.molbulak.smartmoney.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.SmApp
import com.molbulak.smartmoney.cicerone.Screens
import com.molbulak.smartmoney.databinding.ActivityAuthHostBinding

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
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.LoginScreen())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        SmApp.INSTANCE.getNavigator().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        SmApp.INSTANCE.getNavigator().removeNavigator()
    }
}