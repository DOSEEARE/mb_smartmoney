package com.molbulak.smartmoney.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.SmApp
import com.molbulak.smartmoney.cicerone.Screens
import com.molbulak.smartmoney.databinding.FragmentLoginBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.LoginBody
import com.molbulak.smartmoney.util.MyUtil
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
        initLogin()
        initAuth()
    }

    private fun initAuth() {
        binding.authButton.setOnClickListener {
            SmApp.getRouter().navigateTo(Screens.AuthScreen())
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
                        val result = it.data!!.result
                        Log.d("LoginFragment", "initViews: $result")
                        toast("success login ${result.token}")
                        SmApp.getRouter().navigateTo(Screens.MainScreen())
                    }
                    Status.ERROR -> {
                        toast("error login ${data!!.error.code}")
                    }
                    Status.NETWORK -> {
                        toast("Интернет не работает братишка")
                    }
                }
            })
        }
    }


}