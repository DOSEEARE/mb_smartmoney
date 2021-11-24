package com.molbulak.smartmoney.ui.loan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.adapter.NewsAdapter
import com.molbulak.smartmoney.base.BackButtonListener
import com.molbulak.smartmoney.base.HostFragment
import com.molbulak.smartmoney.databinding.FragmentLoanBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanFragment : Fragment(), BackButtonListener {
    lateinit var binding: FragmentLoanBinding
    private val viewModel: LoanViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoanBinding.inflate(inflater, container, false)
        initNews()
        return binding.root
    }

    private fun initNews() {
//        parentActivity<MainHostActivity>().showLoading()
        viewModel.listNews.observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
//                    parentActivity<MainHostActivity>().hideLoading()
                    binding.newsRv.adapter = NewsAdapter(data?.result!!) {
                        App.getRouter().navigateTo(Screens.NewsDetailScreen())
                    }
                    toast("news success ${data.code}")
                }
                Status.ERROR -> {
//                    parentActivity<MainHostActivity>().hideLoading()
                    toast("news error ${data!!.error?.code}")
                }
                Status.NETWORK -> {
//                    parentActivity<MainHostActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    override fun onBackPressed(): Boolean {
        App.getRouter().exit()
        return true
    }
}