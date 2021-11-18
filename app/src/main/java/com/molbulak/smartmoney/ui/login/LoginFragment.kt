package com.molbulak.smartmoney.ui.login

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
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.databinding.FragmentLoginBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.AppPreferences
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.util.MyUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    private var cancellationSignal: CancellationSignal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            initFingerPrint()
        }
        initLogin()
        initAuth()
    }

    private fun initAuth() {
        binding.authButton.setOnClickListener {
            App.getRouter().navigateTo(Screens.CheckNumberScreen())
        }
    }

    private fun initLogin() {
        binding.loginButton.setOnClickListener {
            val login = binding.loginEt.text.toString()
            val password = MyUtil.md5(binding.passwordEt.text.toString())
            val loginBody = LoginBody(
                login = login,
                password = password,
                appid = requireContext().packageName,
                system = 1,
                uid = "1"
            )
            viewModel.login(loginBody).observe(viewLifecycleOwner, {
                val data = it.data
                when (it.status) {
                    Status.SUCCESS -> {
                        App.getRouter().newRootScreen(Screens.MainScreen())
                        AppPreferences.isLogined = true
                        AppPreferences.token = data!!.result!!.token
                    }
                    Status.ERROR -> {
                        toast("error login ${data!!.error?.code}")
                    }
                    Status.NETWORK -> {
                        toast("Проблемы с подключением")
                    }
                }
            })
        }
        binding.forgotButton.setOnClickListener {
            App.getRouter().navigateTo(Screens.RestoreScreen())
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun initFingerPrint() {
        if (!MyUtil.checkBiometricSupport(requireContext())) return
        val cancellationSignal = CancellationSignal()
        cancellationSignal.setOnCancelListener {
            toast("finger print canceled")
        }

        val authenticationCallback: BiometricPrompt.AuthenticationCallback =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    toast("finger print error: code:$errorCode msg:$errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    toast("finger print success")
                }
            }

        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(requireContext())
            .setTitle("Title")
            .setSubtitle("Authenticaion is required")
            .setDescription("Fingerprint Authentication")
            .setNegativeButton("Cancel",
                requireContext().mainExecutor, { dialog, which ->
                    toast("negative button")
                }).build()

        biometricPrompt.authenticate(cancellationSignal,
            requireContext().mainExecutor,
            authenticationCallback)
    }

}

