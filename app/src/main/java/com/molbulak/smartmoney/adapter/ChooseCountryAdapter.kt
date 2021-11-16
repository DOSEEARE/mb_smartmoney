package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.country.CountryResult

class ChooseCountryAdapter(
    private var countries: List<CountryResult>,
    private var selectedCountry: CountryResult?,
    private var listener: SelectListener,
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
        fun bind(country: CountryResult) {
            binding.apply {
                countryName.text = country.name
                if (selectedCountry != null) {
                    if (selectedCountry!!.name == country.name) {
                        binding.countryRadioBtn.isChecked = true
                    }
                }
                binding.root.setOnClickListener {
                    listener.countrySelected(country)
                }
            }
        }
    }
}

interface SelectListener {
    fun countrySelected(country: CountryResult)
}
