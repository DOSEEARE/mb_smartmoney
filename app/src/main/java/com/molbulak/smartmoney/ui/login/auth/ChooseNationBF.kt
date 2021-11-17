package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.adapter.ChooseNationAdapter
import com.molbulak.smartmoney.adapter.SelectNationListener
import com.molbulak.smartmoney.databinding.FragmentChooseBottomBinding
import com.molbulak.smartmoney.service.network.response.nationality.Nation
import java.util.*


class ChooseNationBF(
    private val nations: List<Nation>,
    private val selectedNation: Nation?,
    private val listener: SelectNationListener,
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
        if (selectedNation != null) {
            binding.chooseRv.adapter =
                ChooseNationAdapter(nations, selectedNation, listener)
        } else {
            binding.chooseRv.adapter =
                ChooseNationAdapter(nations, selectedNation, listener)
        }
        binding.closeBtn.setOnClickListener { dismiss() }
        binding.chooseTitle.text = getString(R.string.nationality)
        return binding.root
    }

}

