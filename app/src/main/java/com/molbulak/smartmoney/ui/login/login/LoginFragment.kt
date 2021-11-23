package com.molbulak.smartmoney.ui.login.login

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.FragmentLoginBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.service.preference.AppPreferences
import com.molbulak.smartmoney.service.preference.EncryptedPreferences
import com.molbulak.smartmoney.ui.login.LoginHostActivity
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.util.MyUtil
import com.molbulak.smartmoney.util.enums.LoginType
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChekBoxes()
        initLogin()
        initAuth()
    }

    private fun initChekBoxes() {
        binding.usePinCb.isChecked = AppPreferences.usePinCode
        binding.useFingerprintCb.isChecked = AppPreferences.useFingerprint
        binding.rememberLoginCb.isChecked = AppPreferences.rememberLogin

        binding.usePinCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                binding.useFingerprintCb.isChecked = false

        }
        binding.useFingerprintCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                binding.usePinCb.isChecked = false
        }

        if (AppPreferences.rememberLogin) {
            binding.loginEt.setText(EncryptedPreferences.login)
        }
        if (AppPreferences.useFingerprint) {
            initFingerPrint()
        }
        if (AppPreferences.usePinCode) {
            initPinCode()
        }
    }

    private fun initAuth() {
        binding.authButton.setOnClickListener {
            App.getRouter().navigateTo(Screens.CheckNumberScreen())
        }
    }

    private fun initLogin() {
        binding.loginButton.setOnClickListener {
            login(LoginType.LOGIN_CLICK)
        }
        binding.forgotButton.setOnClickListener {
            App.getRouter().navigateTo(Screens.RestoreScreen())
        }
    }

    fun login(loginType: LoginType) {
        parentActivity<LoginHostActivity>().showLoading()
        val login = binding.loginEt.text.toString()
        val password = MyUtil.md5(binding.passwordEt.text.toString())
        val loginBody = when (loginType) {
            LoginType.LOGIN_CLICK ->
                LoginBody(
                    login = login,
                    password = password,
                    appid = requireContext().packageName,
                    system = 1,
                    uid = "1"
                )
            else -> LoginBody(
                login = EncryptedPreferences.login,
                password = MyUtil.md5(EncryptedPreferences.password),
                appid = requireContext().packageName,
                system = 1,
                uid = "1"
            )
        }

        viewModel.login(loginBody).observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<LoginHostActivity>().hideLoading()

                    AppPreferences.isLogined = true
                    AppPreferences.token = data!!.result!!.token

                    when (loginType) {
                        LoginType.PIN_CODE -> App.getRouter().newRootScreen(Screens.MainScreen())
                        LoginType.FINGERPRINT -> App.getRouter().newRootScreen(Screens.MainScreen())
                        LoginType.LOGIN_CLICK -> {
                            EncryptedPreferences.login = login
                            AppPreferences.login = login
                            EncryptedPreferences.password = binding.passwordEt.text.toString()
                            if (binding.usePinCb.isChecked && AppPreferences.pinCodeIsEmpty())
                                ChoosePinCodeBF().show(childFragmentManager, "ChoosePinCodeBF")
                            else
                                App.getRouter().newRootScreen(Screens.MainScreen())
                        }
                    }
                    AppPreferences.rememberLogin = binding.rememberLoginCb.isChecked
                    AppPreferences.usePinCode = binding.usePinCb.isChecked
                    AppPreferences.useFingerprint = binding.useFingerprintCb.isChecked
                }
                Status.ERROR -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    toast("error login ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    fun pinCodeDismissed() {
        binding.usePinCb.isChecked = false
        AppPreferences.usePinCode = false
        AppPreferences.pinCode = ""
    }

    private fun initPinCode() {
        PinCodeBF().show(childFragmentManager, "PinCodeBF")
    }

    private fun initFingerPrint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (!MyUtil.checkBiometricSupport(requireContext())) return
            val cancellationSignal = CancellationSignal()

            val authenticationCallback: BiometricPrompt.AuthenticationCallback =
                @RequiresApi(Build.VERSION_CODES.P)
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        login(LoginType.FINGERPRINT)
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                        super.onAuthenticationError(errorCode, errString)
                        AppPreferences.useFingerprint = false
                        binding.useFingerprintCb.isChecked = false
                    }
                }

            val biometricPrompt = BiometricPrompt.Builder(requireContext())
                .setTitle(getString(R.string.fingerprint_title))
                .setDescription(getString(R.string.fingerprint_login))
                .setNegativeButton(getString(R.string.cancel),
                    requireContext().mainExecutor, { dialog, _ ->
                        AppPreferences.useFingerprint = false
                        binding.useFingerprintCb.isChecked = false
                        dialog.dismiss()
                    }).build()

            biometricPrompt.authenticate(cancellationSignal,
                requireContext().mainExecutor,
                authenticationCallback)
        }
    }

}

