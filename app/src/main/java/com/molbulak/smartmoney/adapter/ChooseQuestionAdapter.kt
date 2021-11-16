package com.molbulak.smartmoney.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molbulak.smartmoney.databinding.ItemChooseCountryBinding
import com.molbulak.smartmoney.service.network.response.question.Question

class ChooseQuestionAdapter(
    private var questions: List<Question>,
    private var selectedQuestion: Question?,
    private var listener: SelectQuestionListener,
) :
    RecyclerView.Adapter<ChooseQuestionAdapter.ChooseQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseQuestionViewHolder {
        val binding =
            ItemChooseCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChooseQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseQuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    inner class ChooseQuestionViewHolder(private val binding: ItemChooseCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Question) {
            binding.apply {
                countryName.text = country.name
                if (selectedQuestion != null) {
                    if (selectedQuestion!!.name == country.name) {
                        binding.countryRadioBtn.isChecked = true
                    }
                }
                binding.root.setOnClickListener {
                    listener.questionSelected(country)
                }
            }
        }
    }
}

interface SelectQuestionListener {
    fun questionSelected(question: Question)
}
