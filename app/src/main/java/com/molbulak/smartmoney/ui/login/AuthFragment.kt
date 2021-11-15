package com.molbulak.smartmoney.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.molbulak.smartmoney.SmApp
import com.molbulak.smartmoney.adapter.SelectListener
import com.molbulak.smartmoney.databinding.FragmentAuthBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.response.country.CountryResult
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment : Fragment(), SelectListener {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var chooseFragment: ChooseCountryBottomFragment
    private val viewModel: LoginViewModel by viewModel()
    private val availableCountries = MutableLiveData<List<CountryResult>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener { SmApp.getRouter().exit() }
        initAvailableCountries()
        initChooseCountry()
    }

    private fun initChooseCountry() {
        binding.countryDrop.setOnClickListener {
            chooseFragment.show(childFragmentManager, "ChooseCountryBottomFragment")
        }
    }

    private fun initAvailableCountries() {
        chooseFragment = ChooseCountryBottomFragment(availableCountries, this)
        viewModel.availableCountry().observe(viewLifecycleOwner, {
            val data = it.data
            when (it.status) {
                Status.SUCCESS -> {
                    availableCountries.value = it.data?.result!!
                }
                Status.ERROR -> {
                    toast("error country ${data!!.error?.code}")
                }
                Status.NETWORK -> {
                    toast("Интернет не работает братишка")
                }
            }
        })
    }

    override fun countrySelected(country: CountryResult) {
        chooseFragment.dismiss()
    }

}