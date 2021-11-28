package com.molbulak.smartmoney.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.MainHostActivity
import com.molbulak.smartmoney.adapter.FaqAdapter
import com.molbulak.smartmoney.databinding.FragmentSupportBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.util.enums.ContainerType
import org.koin.androidx.viewmodel.ext.android.viewModel

class SupportFragment : Fragment() {
    private val viewModel: SupportViewModel by viewModel()

    private lateinit var binding: FragmentSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSupportBinding.inflate(inflater, container, false)
        initViews()
        binding.toolbar.tbBackBtn.isVisible = false
        binding.toolbar.tbTitleTv.text = "FAQ"
        return binding.root
    }

    private fun initViews() {
        parentActivity<MainHostActivity>().showLoading()
        viewModel.listFaq.observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<MainHostActivity>().hideLoading()
                    binding.supportRv.adapter = FaqAdapter(data?.result!!)
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