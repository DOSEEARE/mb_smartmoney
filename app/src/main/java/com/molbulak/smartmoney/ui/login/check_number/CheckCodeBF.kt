package com.molbulak.smartmoney.ui.login.check_number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.databinding.FragmentCheckCodeBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.CheckCodeBody
import com.molbulak.smartmoney.ui.login.LoginHostActivity
import com.molbulak.smartmoney.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CheckCodeBF(
    private val requestId: Int,
    private val listener: CheckCodeListener,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCheckCodeBinding
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckCodeBinding.inflate(inflater, container, false)
        binding.run {
            acceptBtn.setOnClickListener {
                if (!smsEt.text.isNullOrBlank()) {
                    val smsCode = smsEt.text.toString()
                    checkCode(CheckCodeBody(smsCode, requestId))
                }
            }
        }
        return binding.root
    }

    private fun checkCode(checkCodeBody: CheckCodeBody) {
        parentActivity<LoginHostActivity>().showLoading()
        viewModel.checkCode(checkCodeBody).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    dismiss()
                    listener.codeChecked()
                }
                Status.ERROR -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    if (it.data!!.error?.code == 400) {
                        binding.wrongIndicatorTv.isVisible = true
                    }
                }
                Status.NETWORK -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })

    }
}

fun interface CheckCodeListener {
    fun codeChecked()
}