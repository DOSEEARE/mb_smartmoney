package com.molbulak.smartmoney.ui.loan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.databinding.FragmentNewsDetailBinding
import com.molbulak.smartmoney.ui.BackButtonListener


class NewsDetailFragment : Fragment(), BackButtonListener {
    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackPressed():Boolean {
        App.getRouter().exit()
        return true
    }

}