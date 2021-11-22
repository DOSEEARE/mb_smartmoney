package com.molbulak.smartmoney.ui.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.custom.passwordview.ActionListener
import com.molbulak.smartmoney.databinding.FragmentPinCodeBfBinding
import com.molbulak.smartmoney.extensions.parentFragment
import com.molbulak.smartmoney.service.preference.AppPreferences

class PinCodeBF : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPinCodeBfBinding
    private val correctPin = AppPreferences.pinCode
    private var successPin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPinCodeBfBinding.inflate(inflater, container, false)
        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.run {
            pin0.setOnClickListener { pinView.appendInputText("0") }
            pin1.setOnClickListener { pinView.appendInputText("1") }
            pin2.setOnClickListener { pinView.appendInputText("2") }
            pin3.setOnClickListener { pinView.appendInputText("3") }
            pin4.setOnClickListener { pinView.appendInputText("4") }
            pin5.setOnClickListener { pinView.appendInputText("5") }
            pin6.setOnClickListener { pinView.appendInputText("6") }
            pin7.setOnClickListener { pinView.appendInputText("7") }
            pin8.setOnClickListener { pinView.appendInputText("8") }
            pin9.setOnClickListener { pinView.appendInputText("9") }
            pin0.setOnClickListener { pinView.appendInputText("0") }

            pinCancel.setOnClickListener {
                parentFragment<LoginFragment>().pinCodeDismissed()
                dismiss() }

            pinDelete.setOnClickListener { pinView.removeInputText() }

            pinView.setListener(object : ActionListener {
                override fun onCompleteInput(inputText: String) {
                    if (inputText == correctPin) {
                        pinView.correctAnimation()
                        successPin = true
                    } else {
                        pinView.incorrectAnimation()
                    }
                }

                override fun onEndJudgeAnimation() {
                    if (successPin) {
                        pinView.removeListener()
                        parentFragment<LoginFragment>().login(LoginType.PIN_CODE)
                    } else {
                        pinView.reset()
                    }
                }
            })
        }
    }

}