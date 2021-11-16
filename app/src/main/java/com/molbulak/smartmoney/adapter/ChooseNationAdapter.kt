package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.nationality.Nation

class ChooseNationAdapter(
    private var nations: List<Nation>,
    private var selectedNation: Nation?,
    private var listener: SelectNationListener,
) :
    RecyclerView.Adapter<ChooseNationAdapter.ChooseNationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseNationViewHolder {
        val binding =
            ItemChooseCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseNationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseNationViewHolder, position: Int) {
        holder.bind(nations[position])
    }

    override fun getItemCount(): Int {
        return nations.size
    }

    inner class ChooseNationViewHolder(private val binding: ItemChooseCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Nation) {
            binding.apply {
                countryName.text = country.name
                if (selectedNation != null) {
                    if (selectedNation!!.name == country.name) {
                        binding.countryRadioBtn.isChecked = true
                    }
                }
                binding.root.setOnClickListener {
                    listener.nationSelected(country)
                }
            }
        }
    }
}

interface SelectNationListener {
    fun nationSelected(nation: Nation)
}
