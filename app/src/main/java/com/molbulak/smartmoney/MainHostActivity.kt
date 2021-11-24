package com.molbulak.smartmoney

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.adapter.ViewPagerAdapter
import com.molbulak.smartmoney.databinding.ActivityMainHostBinding
import com.molbulak.smartmoney.databinding.DialogLoadingBinding
import com.molbulak.smartmoney.databinding.DialogSuccessBinding
import com.molbulak.smartmoney.ui.ContainerFragment
import com.molbulak.smartmoney.ui.loan.LoanFragment
import com.molbulak.smartmoney.ui.more.MoreFragment
import com.molbulak.smartmoney.ui.notification.NotificationFragment
import com.molbulak.smartmoney.ui.profile.ProfileFragment
import com.molbulak.smartmoney.ui.support.SupportFragment
import com.molbulak.smartmoney.util.ClickListener
import com.molbulak.smartmoney.util.enums.FragmentType


class MainHostActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog
    private lateinit var binding: ActivityMainHostBinding


    private lateinit var navigator: AppNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = object : AppNavigator(this, R.id.container) {}
        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNav()
    }

    private fun initBottomNav() {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
/*        pagerAdapter.addFragment(LoanFragment())
        pagerAdapter.addFragment(NotificationFragment())
        pagerAdapter.addFragment(ProfileFragment())
        pagerAdapter.addFragment(SupportFragment())
        pagerAdapter.addFragment(MoreFragment())*/

        pagerAdapter.addFragment(ContainerFragment(FragmentType.LOAN))
        pagerAdapter.addFragment(ContainerFragment(FragmentType.NOTIFICATION))
        pagerAdapter.addFragment(ContainerFragment(FragmentType.PROFILE))
        pagerAdapter.addFragment(ContainerFragment(FragmentType.SUPPORT))
        pagerAdapter.addFragment(ContainerFragment(FragmentType.MORE))

        binding.mainContainer.adapter = pagerAdapter
        val bottomNavigationView = binding.navView
        binding.mainContainer.offscreenPageLimit = 4
        bottomNavigationView.setOnItemSelectedListener { item ->
            binding.mainContainer.post {
                when (item.itemId) {
                    R.id.nav_loan -> {
                        binding.mainContainer.setCurrentItem(0, false)
                    }
                    R.id.nav_notification -> {
                        binding.mainContainer.setCurrentItem(1, false)
                    }
                    R.id.nav_profile -> {
                        binding.mainContainer.setCurrentItem(2, false)
                    }
                    R.id.nav_support -> {
                        binding.mainContainer.setCurrentItem(3, false)
                    }
                    R.id.nav_more -> {
                        binding.mainContainer.setCurrentItem(4, false)
                    }
                }
            }
            true
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