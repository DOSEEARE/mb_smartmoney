package com.molbulak.smartmoney

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.molbulak.smartmoney.adapter.ViewPagerAdapter
import com.molbulak.smartmoney.databinding.ActivityMainHostBinding
import com.molbulak.smartmoney.databinding.DialogLoadingBinding
import com.molbulak.smartmoney.databinding.DialogSuccessBinding
import com.molbulak.smartmoney.ui.BackButtonListener
import com.molbulak.smartmoney.ui.ContainerFragment
import com.molbulak.smartmoney.util.ClickListener
import com.molbulak.smartmoney.util.enums.ContainerType


class MainHostActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog
    private lateinit var binding: ActivityMainHostBinding
    private lateinit var pagerAdapter: ViewPagerAdapter
    private var currentFragmentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNav()
    }

    private fun initBottomNav() {
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(ContainerFragment(ContainerType.LOAN))
        pagerAdapter.addFragment(ContainerFragment(ContainerType.NOTICE))
        pagerAdapter.addFragment(ContainerFragment(ContainerType.PROFILE))
        pagerAdapter.addFragment(ContainerFragment(ContainerType.SUPPORT))
        pagerAdapter.addFragment(ContainerFragment(ContainerType.MORE))

        binding.mainContainer.adapter = pagerAdapter
        val bottomNavigationView = binding.navView
        binding.mainContainer.offscreenPageLimit = 4
        bottomNavigationView.setOnItemSelectedListener { item ->
            binding.mainContainer.post {
                when (item.itemId) {
                    R.id.nav_loan -> {
                        binding.mainContainer.setCurrentItem(0, false)
                        currentFragmentPos = 0
                    }
                    R.id.nav_notification -> {
                        binding.mainContainer.setCurrentItem(1, false)
                        currentFragmentPos = 1
                    }
                    R.id.nav_profile -> {
                        binding.mainContainer.setCurrentItem(2, false)
                        currentFragmentPos = 2
                    }
                    R.id.nav_support -> {
                        binding.mainContainer.setCurrentItem(3, false)
                        currentFragmentPos = 3
                    }
                    R.id.nav_more -> {
                        binding.mainContainer.setCurrentItem(4, false)
                        currentFragmentPos = 4
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        val currentFragment = pagerAdapter.getItem(currentFragmentPos)
        (currentFragment as BackButtonListener).backPressed()
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