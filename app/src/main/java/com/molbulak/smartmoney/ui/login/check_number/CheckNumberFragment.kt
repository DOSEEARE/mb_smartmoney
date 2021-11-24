package com.molbulak.smartmoney.ui.login.check_number

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.adapter.SelectCountryListener
import com.molbulak.smartmoney.databinding.FragmentCheckNumberBinding
import com.molbulak.smartmoney.extensions.parentActivity
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.CheckPhoneBody
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.ui.login.LoginHostActivity
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.util.MyUtil
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class CheckNumberFragment : Fragment(), SelectCountryListener {
    private lateinit var binding: FragmentCheckNumberBinding
    private lateinit var chooseFragment: ChooseCountryBF
    private val viewModel: LoginViewModel by viewModel()
    private var availableCountries = listOf<Country>()
    private var selectedCountry: Country? = null

    private var inputMask =
        MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER))

    private val router : Router by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener { router.exit() }
        binding.numberPhone.inputType = InputType.TYPE_CLASS_PHONE
        binding.nextBtn.setOnClickListener {
            checkNumberPhone()
        }
        initAvailableCountries()
    }


    private fun initAvailableCountries() {
        parentActivity<LoginHostActivity>().showLoading()
        chooseFragment = ChooseCountryBF(availableCountries, selectedCountry, this)
        viewModel.availableCountry().observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    availableCountries = (it.data?.result!!)
                    binding.countryDrop.setOnClickListener {
                        chooseFragment =
                            ChooseCountryBF(availableCountries, selectedCountry, this)
                        chooseFragment.show(childFragmentManager, "ChooseCountryBottomFragment")
                    }
                }
                Status.ERROR -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    toast("error country ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    parentActivity<LoginHostActivity>().hideLoading()
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    private fun checkNumberPhone() {
        parentActivity<LoginHostActivity>().showLoading()
        val notFormatNumber = binding.numberPhone.text.toString()
        val formatNumber = MyUtil.onlyDigits(notFormatNumber)
        if (notFormatNumber.isEmpty()) return
        if (formatNumber.length != selectedCountry!!.phone_length.toInt()) {
            binding.numberPhone.error = getString(R.string.wrong_format); return
        }
        viewModel.checkPhone(CheckPhoneBody(formatNumber)).observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    CheckCodeBF(data?.result!!.id) {
                        router
                            .navigateTo(Screens.AuthScreen(selectedCountry!!, notFormatNumber))
                    }.show(childFragmentManager, "CheckCodeBF")
                }
                Status.ERROR -> {
                    toast("error country ${data?.error?.code} ${data?.error?.message}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }
            parentActivity<LoginHostActivity>().hideLoading()
        })
    }

    override fun countrySelected(country: Country) {
        inputMask.removeFromTextView()
        binding.numberPhone.setText("")
        inputMask = MyUtil.inputMask(country.phone_mask)
        inputMask.installOn(binding.numberPhone)
        binding.countryDrop.setText(country.name)
        selectedCountry = country
        chooseFragment.dismiss()
    }

}