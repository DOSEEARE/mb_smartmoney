package com.molbulak.smartmoney.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.databinding.FragmentNoticeDetailBinding
import com.molbulak.smartmoney.service.network.response.notice.Notice
import com.molbulak.smartmoney.util.enums.ContainerType


class NoticeDetailFragment(notice: Notice) : Fragment() {
    private lateinit var binding: FragmentNoticeDetailBinding
    private lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoticeDetailBinding.inflate(inflater, container, false)
        router = App.localCicerone.getCicerone(ContainerType.NOTICE).router
        binding.toolbar.tbTitleTv.text = getString(R.string.title_notification)

        binding.toolbar.tbBackBtn.setOnClickListener {
            router.exit()
        }
        return binding.root
    }


}
