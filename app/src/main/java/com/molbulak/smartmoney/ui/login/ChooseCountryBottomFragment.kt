package com.molbulak.smartmoney.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.adapter.ChooseCountryAdapter
import com.molbulak.smartmoney.adapter.SelectListener
import com.molbulak.smartmoney.databinding.FragmentChooseCountryBottomBinding
import com.molbulak.smartmoney.service.network.response.country.CountryResult


class ChooseCountryBottomFragment(
    private val countries: MutableLiveData<List<CountryResult>>,
    val listener: SelectListener,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentChooseCountryBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseCountryBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        countries.observe(viewLifecycleOwner, {
            binding.chooseCountryRv.adapter = ChooseCountryAdapter(it, listener)
        })
    }

}