package com.molbulak.smartmoney.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.cicerone.Screens
import com.molbulak.smartmoney.databinding.ActivityAuthHostBinding

class LoginHostActivity : AppCompatActivity() {
    private val navigator = object : AppNavigator(this, R.id.auth_container) {
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
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
        App.INSTANCE.getNavigator().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.getNavigator().removeNavigator()
    }
}