package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemNoticeBinding
import com.molbulak.smartmoney.service.network.response.notice.Notice

class NoticeAdapter(
    private var notice: List<Notice>,
    private var countryListener: NoticeClicked,
) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding =
            ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(notice[position])
    }

    override fun getItemCount(): Int {
        return notice.size
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: Notice) {
            binding.run {
                titleTv.text = notice.title
                dateTv.text = notice.date
                readIndicatorImg.isVisible = !notice.review
                totalTv.text = notice.description
                if (notice.detail)
                    root.setOnClickListener {
                        countryListener.noticeClicked(notice)
                    }
            }

        }
    }
}

fun interface NoticeClicked {
    fun noticeClicked(notice: Notice)
}
