package com.molbulak.smartmoney.ui.login.check_number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.adapter.ChooseCountryAdapter
import com.molbulak.smartmoney.adapter.SelectListener
import com.molbulak.smartmoney.databinding.FragmentChooseBottomBinding
import com.molbulak.smartmoney.service.network.response.country.Country


class ChooseCountryBottomFragment(
    private val countries: List<Country>,
    private val selectedCountry: Country?,
    private val listener: SelectListener,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentChooseBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseBottomBinding.inflate(inflater, container, false)
        if (selectedCountry != null) {
            binding.chooseRv.adapter =
                ChooseCountryAdapter(countries, selectedCountry, listener)
        }else{
            binding.chooseRv.adapter =
                ChooseCountryAdapter(countries, selectedCountry, listener)
        }
        binding.closeBtn.setOnClickListener { dismiss() }
        return binding.root
    }

}