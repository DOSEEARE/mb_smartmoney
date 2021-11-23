package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.molbulak.smartmoney.databinding.ItemNewsBinding
import com.molbulak.smartmoney.service.network.response.news.News

class NewsAdapter(
    private var news: List<News>,
    private var countryListener: NewsClickListener,
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.apply {
                newsImg.load(news.thumbnail)
                titleTv.text = news.name
                contentTv.text = news.description
                binding.root.setOnClickListener {
                    countryListener.newsClicked(news)
                }
            }
        }
    }
}

fun interface NewsClickListener {
    fun newsClicked(news: News)
}
