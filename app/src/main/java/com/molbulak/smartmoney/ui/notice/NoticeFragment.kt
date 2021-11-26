package com.molbulak.smartmoney.ui.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.adapter.NoticeAdapter
import com.molbulak.smartmoney.databinding.FragmentNotificationBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.ui.BackButtonListener
import com.molbulak.smartmoney.util.enums.ContainerType
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeFragment : Fragment(), BackButtonListener {
    private val viewModel: NoticeViewModel by viewModel()
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var router : Router
 //   private val router = (parentFragment as ContainerFragment).router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        router = App.localCicerone.getCicerone(ContainerType.NOTICE).router
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.toolbar.tbTitleTv.text = getString(R.string.title_notification)
        binding.toolbar.tbBackBtn.isVisible = false
        viewModel.listNoticeList.observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    binding.notificationRv.adapter = NoticeAdapter(data?.result!!) {
                        router.navigateTo(Screens.NoticeDetailScreen(it))
                    }
                }
                Status.ERROR -> {
                    toast("notice error ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }

        })

    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }

}