package com.molbulak.smartmoney.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.adapter.ViewPagerAdapter
import com.molbulak.smartmoney.databinding.FragmentProfileHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileHistoryFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileHistoryBinding.inflate(inflater, container, false)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(HistoryFragment())
        viewPagerAdapter.addFragment(HistoryFragment())
        binding.historyVp.adapter = viewPagerAdapter
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.myOperationsTv.setOnClickListener { viewModel.selectedScreen.postValue(true) }
        binding.myBidsTv.setOnClickListener { viewModel.selectedScreen.postValue(false) }

        viewModel.selectedScreen.observe(viewLifecycleOwner, {
            if (it) {
                binding.myOperationsTv.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_profile_selected)
                binding.myBidsTv.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_profile_unselected)
                binding.historyVp.currentItem = 0
            } else {
                binding.myBidsTv.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_profile_selected)
                binding.myOperationsTv.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_profile_unselected)
                binding.historyVp.currentItem = 1
            }
        })

    }


}