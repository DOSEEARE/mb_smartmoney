package com.molbulak.smartmoney.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.molbulak.smartmoney.databinding.FragmentProfileBinding
import com.molbulak.smartmoney.databinding.FragmentSupportBinding

class SupportFragment : Fragment() {

    private lateinit var binding: FragmentSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSupportBinding.inflate(inflater, container, false)

        return binding.root
    }

}