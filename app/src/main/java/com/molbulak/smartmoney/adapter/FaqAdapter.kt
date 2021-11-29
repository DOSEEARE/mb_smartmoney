package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.custom.image_getter.ImageGetter
import com.molbulak.smartmoney.databinding.ItemSupportBinding
import com.molbulak.smartmoney.service.network.response.faq.Faq

class FaqAdapter(
    private var faq: List<Faq>
) :
    RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {
    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding =
            ItemSupportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(faq[position], position)
    }

    override fun getItemCount(): Int {
        return faq.size
    }

    inner class FaqViewHolder(private val binding: ItemSupportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: Faq, position: Int) {
            binding.apply {
                binding.expandLayout.isVisible = false
                binding.expandLayout.collapse()
                titleTv.text = faq.name
                binding.contentTv.text = HtmlCompat.fromHtml(
                    faq.text,
                    HtmlCompat.FROM_HTML_MODE_LEGACY, ImageGetter(
                        binding.root.context,
                        binding.root.context.resources, binding.contentTv
                    ), null
                )
            }
            binding.root.setOnClickListener {
                if (checkedPosition == -1) {
                    binding.expandLayout.collapse()
                    binding.arrowImg.rotateImg()
                } else {
                    if (checkedPosition == position) {
                        binding.expandLayout.isVisible = true
                        binding.expandLayout.expand(true)
                        binding.arrowImg.rotateImg()
                    } else {
                        binding.expandLayout.collapse()
                        binding.arrowImg.rotateImg()
                    }
                }
                if (checkedPosition != position) {
                    binding.expandLayout.isVisible = true
                    binding.expandLayout.expand()
                    binding.arrowImg.rotateImg()
                    notifyItemChanged(checkedPosition)
                    checkedPosition = position
                }
            }
        }
    }

    private fun ImageView.rotateImg() {
        animate().rotation(180.0F).start()
    }
}

