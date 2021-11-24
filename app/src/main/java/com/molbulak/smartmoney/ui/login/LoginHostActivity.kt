package com.molbulak.smartmoney.ui.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.base.HostActivity
import com.molbulak.smartmoney.databinding.ActivityAuthHostBinding
import com.molbulak.smartmoney.databinding.DialogLoadingBinding
import com.molbulak.smartmoney.databinding.DialogSuccessBinding
import com.molbulak.smartmoney.util.ClickListener

class LoginHostActivity : HostActivity() {
    override val navigator = object : AppNavigator(this, R.id.auth_container) {

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
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthHostBinding.inflate(layoutInflater)
        loadingDialog = Dialog(this)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.LoginScreen())))
        }
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
        val binding: DialogSuccessBinding = DialogSuccessBinding.inflate(LayoutInflater.from(this))
        //<---------set view under of this------------->
        binding.contentTv.text = contentText
        binding.titleTv.text = titleText
        binding.acceptBtn.text = btnText
        binding.acceptBtn.setOnClickListener { listener.btnClicked(dialog) }
        //<---------set view top of this------------->
        dialog.setContentView(binding.root)
        dialog.show()
    }

    fun showLoading() {
        loadingDialog = Dialog(this)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        loadingDialog.setCancelable(false)
        val binding: DialogLoadingBinding = DialogLoadingBinding.inflate(LayoutInflater.from(this))
        loadingDialog.setContentView(binding.root)
        loadingDialog.show()
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }
}