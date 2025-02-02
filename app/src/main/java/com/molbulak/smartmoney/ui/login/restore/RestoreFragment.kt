package com.molbulak.smartmoney.ui.login.restore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.adapter.SelectCountryListener
import com.molbulak.smartmoney.databinding.FragmentRestoreBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.RestoreBody
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.ui.login.LoginBaseActivity
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.ui.login.check_number.ChooseCountryBF
import com.molbulak.smartmoney.util.MyUtil
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class RestoreFragment : Fragment(), SelectCountryListener {
    private lateinit var binding: FragmentRestoreBinding
    private val viewModel: LoginViewModel by viewModel()

    private var availableCountries = listOf<Country>()
    private var selectedCountry: Country? = null
    private var chooseCountryBF = ChooseCountryBF(availableCountries, null, null)

    private var inputMask =
        MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER))
    private val router: Router by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRestoreBinding.inflate(layoutInflater, container, false)
        binding.backBtn.setOnClickListener { router.exit() }
        initCountry()
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.nextBtn.setOnClickListener {
            if (checkFields()) initRestore()
        }
    }

    private fun initRestore() {
        parentActivity<LoginBaseActivity>().showLoading()
        val number = MyUtil.onlyDigits(binding.numberPhone.text.toString())
        val secretWord = binding.secretWord.text.toString()
        val body = RestoreBody(number, secretWord)
        viewModel.restore(body).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    parentActivity<LoginBaseActivity>().showSuccess(
                        getString(R.string.success_restore),
                        getString(R.string.loginpas_sms),
                        getString(R.string.accept)
                    ) { dialog ->
                        dialog.dismiss()
                        router.newRootScreen(Screens.LoginScreen())
                    }
                    toast("restore success ${it.data!!.error?.code}")
                }
                Status.ERROR -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    toast("restore error ${it.data!!.error?.code}")
                }
                Status.NETWORK -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    private fun checkFields(): Boolean {
        var success = true
        val fields = binding.run {
            listOf(numberPhone, secretWord)
        }
        fields.forEach {
            if (it.text.isNullOrBlank()) {
                success = false
                it.error = getString(R.string.warning_empty)
            }
        }
        return success
    }

    private fun initCountry() {
        parentActivity<LoginBaseActivity>().showLoading()
        viewModel.availableCountry().observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    availableCountries = (it.data?.result!!)
                    binding.countryDb.setOnClickListener {
                        chooseCountryBF = ChooseCountryBF(availableCountries, selectedCountry, this)
                        chooseCountryBF.show(childFragmentManager, "ChooseCountryBottomFragment")
                    }
                    binding.countryDb.setText(data?.result!![0].name)
                    this.countrySelected((data.result[0]))
                }
                Status.ERROR -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    toast("error country ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    parentActivity<LoginBaseActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    override fun countrySelected(country: Country) {
        inputMask.removeFromTextView()
        binding.numberPhone.setText("")
        inputMask = MyUtil.inputMask(country.phone_mask)
        inputMask.installOn(binding.numberPhone)
        binding.countryDb.setText(country.name)
        selectedCountry = country
        chooseCountryBF.dismiss()
    }

}