package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.gender.Gender

class ChooseGenderAdapter(
    private var genders: List<Gender>,
    private var selectedGender: Gender?,
    private var listener: SelectGenderListener,
) :
    RecyclerView.Adapter<ChooseGenderAdapter.ChooseGenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseGenderViewHolder {
        val binding =
            ItemChooseCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseGenderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseGenderViewHolder, position: Int) {
        holder.bind(genders[position])
    }

    override fun getItemCount(): Int {
        return genders.size
    }

    inner class ChooseGenderViewHolder(private val binding: ItemChooseCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Gender) {
            binding.apply {
                countryName.text = country.name
                if (selectedGender != null) {
                    if (selectedGender!!.name == country.name) {
                        binding.countryRadioBtn.isChecked = true
                    }
                }
                binding.root.setOnClickListener {
                    listener.genderSelected(country)
                }
            }
        }
    }
}

interface SelectGenderListener {
    fun genderSelected(gender: Gender)
}
