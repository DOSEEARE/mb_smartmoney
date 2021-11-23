package com.molbulak.smartmoney

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.databinding.ActivityMainHostBinding
import com.molbulak.smartmoney.databinding.DialogLoadingBinding
import com.molbulak.smartmoney.databinding.DialogSuccessBinding
import com.molbulak.smartmoney.ui.BackButtonListener
import com.molbulak.smartmoney.ui.ContainerFragment
import com.molbulak.smartmoney.util.ClickListener
import com.molbulak.smartmoney.util.enums.FragmentType


class MainHostActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog
    private lateinit var binding: ActivityMainHostBinding

    private lateinit var lastFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setOnClickListener {
            App.getRouter().navigateTo(Screens.LoginScreen())
        }
        initBottomNav(savedInstanceState)
    }

    private fun initBottomNav(savedInstanceState: Bundle?) {
        val bottomNavigationView = binding.navView
        val loanFragment = ContainerFragment(FragmentType.LOAN)
        val notificationFragment = ContainerFragment(FragmentType.NOTIFICATION)
        val profileFragment = ContainerFragment(FragmentType.PROFILE)
        val supportFragment = ContainerFragment(FragmentType.SUPPORT)
        val moreFragment = ContainerFragment(FragmentType.MORE)
        lastFragment = loanFragment


        //Первая страница - последняя в комите
        /*   supportFragmentManager.beginTransaction().run {
               add(binding.mainContainer.id, notificationFragment)
               add(binding.mainContainer.id, supportFragment)
               add(binding.mainContainer.id, moreFragment)
               add(binding.mainContainer.id, loanFragment)
               add(binding.mainContainer.id, profileFragment)
               commit()
           }*/
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_loan -> {
                    // navigateTo(loanFragment, loanTAG)
                    navigateTo(FragmentType.LOAN)
                }
                R.id.nav_notification -> {
                    //       navigateTo(notificationFragment, notificationTAG)
                    navigateTo(FragmentType.NOTIFICATION)
                }
                R.id.nav_profile -> {
                    //       navigateTo(profileFragment, profileTAG)
                    navigateTo(FragmentType.PROFILE)
                }
                R.id.nav_support -> {
                    //       navigateTo(supportFragment, supportTAG)
                    navigateTo(FragmentType.SUPPORT)
                }
                R.id.nav_more -> {
                    //       navigateTo(moreFragment, moreTAG)
                    navigateTo(FragmentType.MORE)
                }
            }
            true
        }
    }

    private fun navigateTo(fragmentType: FragmentType) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(fragmentType.toString())
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                binding.mainContainer.id,
                Screens.ContainerScreen(fragmentType).createFragment(fm.fragmentFactory),
                fragmentType.toString()
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
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

    companion object {
        private val loanTAG = "loan"
        private val profileTAG = "loan"
        private val supportTAG = "loan"
        private val moreTAG = "loan"
        private val notificationTAG = "loan"
    }
}