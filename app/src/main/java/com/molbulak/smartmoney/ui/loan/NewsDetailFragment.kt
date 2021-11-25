package com.molbulak.smartmoney.ui.loan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.databinding.FragmentNewsDetailBinding
import com.molbulak.smartmoney.service.network.response.news.News
import com.molbulak.smartmoney.util.enums.ContainerType


class NewsDetailFragment(private val news: News) : Fragment() {
    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.run {
            toolbar.tbTitleTv.text = getString(R.string.news)
            imgView.load(news.thumbnail)
            titleTv.text = news.name
            contentTv.text = news.description

            toolbar.tbBackBtn.setOnClickListener {
                App.localCicerone.getCicerone(ContainerType.LOAN).router
            }
        }
    }


}