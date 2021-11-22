package com.molbulak.smartmoney.ui.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.FragmentChoosePinCodeBBinding
import com.molbulak.smartmoney.extensions.parentFragment
import com.molbulak.smartmoney.service.preference.AppPreferences


class ChoosePinCodeBF : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentChoosePinCodeBBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChoosePinCodeBBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.run {
            closeBtn.setOnClickListener {
                parentFragment<LoginFragment>().pinCodeDismissed()
                dismiss()
            }
            continueBtn.setOnClickListener {
                val pinCode1 = pinCode.text.toString()
                val pinCode2 = repeatPinCode.text.toString()
                if (pinCode1.isNotEmpty() && pinCode2.isNotEmpty()) {
                    if (pinCode1 == pinCode2) {
                            AppPreferences.pinCode = pinCode.text.toString()
                            App.getRouter().newRootScreen(Screens.MainScreen())
                    }
                }
            }

        }
    }
}