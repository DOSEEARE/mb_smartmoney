package com.molbulak.smartmoney.ui.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.MainHostActivity
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.custom.image_getter.ImageGetter
import com.molbulak.smartmoney.databinding.FragmentNoticeDetailBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.response.notice.Notice
import com.molbulak.smartmoney.util.enums.ContainerType
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeDetailFragment(val notice: Notice) : Fragment() {
    private lateinit var binding: FragmentNoticeDetailBinding
    private val viewModel: NoticeViewModel by viewModel()

    private lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoticeDetailBinding.inflate(inflater, container, false)
        router = App.localCicerone.getCicerone(ContainerType.NOTICE).router
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.toolbar.tbTitleTv.text = getString(R.string.title_notification)

        binding.toolbar.tbBackBtn.setOnClickListener {
            router.exit()
        }
        parentActivity<MainHostActivity>().showLoading()
        viewModel.noticeDetail(notice.id).observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    val notice = data?.result!!
                    parentActivity<MainHostActivity>().hideLoading()
                    binding.titleTv.text = notice.title
                    binding.descriptionTv.text = notice.description
                    binding.contentTv.text = HtmlCompat.fromHtml(notice.text,
                        HtmlCompat.FROM_HTML_MODE_LEGACY, ImageGetter(requireContext(),
                            resources, binding.contentTv), null)
                }
                Status.ERROR -> {
                    parentActivity<MainHostActivity>().hideLoading()
                    toast("notice error ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    parentActivity<MainHostActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }

        })
    }


}
