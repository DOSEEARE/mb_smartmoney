package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.adapter.ChooseGenderAdapter
import com.molbulak.smartmoney.adapter.SelectGenderListener
import com.molbulak.smartmoney.databinding.FragmentChooseBottomBinding
import com.molbulak.smartmoney.service.network.response.gender.Gender


class ChooseGenderBF(
    private val genders: List<Gender>,
    private val selectedCountry: Gender?,
    private val listener: SelectGenderListener,
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
                ChooseGenderAdapter(genders, selectedCountry, listener)
        } else {
            binding.chooseRv.adapter =
                ChooseGenderAdapter(genders, selectedCountry, listener)
        }
        binding.closeBtn.setOnClickListener { dismiss() }
        binding.chooseTitle.text = getString(R.string.gender)
        return binding.root
    }

}