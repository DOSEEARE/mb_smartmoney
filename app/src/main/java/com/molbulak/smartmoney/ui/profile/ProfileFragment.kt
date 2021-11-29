package com.molbulak.smartmoney.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.adapter.ViewPagerAdapter
import com.molbulak.smartmoney.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    private var currentItem = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.tbBackBtn.setVisible(false)

        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(ProfileHistoryFragment())
        pagerAdapter.addFragment(ProfileDetailFragment())
        binding.profileVp.adapter = pagerAdapter

        binding.showProfileTv.setOnClickListener {
            binding.profileVp.currentItem = 1
            currentItem = binding.profileVp.currentItem
            binding.tbBackBtn.setVisible(true)
        }

        binding.tbBackBtn.setOnClickListener {
            if (currentItem == 1) {
                binding.profileVp.currentItem = 0
                currentItem = binding.profileVp.currentItem
                binding.tbBackBtn.setVisible(false)
            }
        }
        return binding.root
    }

    private fun View.setVisible(boolean: Boolean) {
        if (boolean)
            animate().alpha(1.0f).start()
        else
            animate().alpha(0.0f).start()
    }


}