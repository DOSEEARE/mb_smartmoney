package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.country.Country

class ChooseCountryAdapter(
    private var countries: List<Country>,
    private var selectedCountry: Country?,
    private var countryListener: SelectCountryListener,
) :
    RecyclerView.Adapter<ChooseCountryAdapter.ChooseCountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCountryViewHolder {
        val binding =
            ItemChooseCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseCountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseCountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class ChooseCountryViewHolder(private val binding: ItemChooseCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                countryName.text = country.name
                if (selectedCountry != null) {
                    if (selectedCountry!!.name == country.name) {
                        binding.countryRadioBtn.isChecked = true
                    }
                }
                binding.root.setOnClickListener {
                    countryListener.countrySelected(country)
                }
            }
        }
    }
}

interface SelectCountryListener {
    fun countrySelected(country: Country)
}
