package com.molbulak.smartmoney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.molbulak.smartmoney.cicerone.Screens
import com.molbulak.smartmoney.databinding.ActivityMainHostBinding

class MainHostActivity : AppCompatActivity() {

    private val navigator = object : AppNavigator(this, R.id.main_container) {
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }
    }

    private lateinit var binding: ActivityMainHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setOnClickListener {
            SmApp.getRouter().navigateTo(Screens.LoginScreen())
        }

    }

    override fun onResume() {
        super.onResume()
        SmApp.INSTANCE.getNavigator().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        SmApp.INSTANCE.getNavigator().removeNavigator()
    }
}