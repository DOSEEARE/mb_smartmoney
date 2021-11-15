package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.country.CountryResult

class ChooseCountryAdapter(
    private var countries: List<CountryResult>,
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
                binding.root.setOnClickListener {
                    Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
                    listener.countrySelected(country) }
            }
        }
    }
}

interface SelectListener {
    fun countrySelected(country: CountryResult)
}
