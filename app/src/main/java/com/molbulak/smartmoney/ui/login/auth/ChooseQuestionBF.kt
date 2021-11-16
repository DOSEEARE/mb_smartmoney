package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.adapter.ChooseQuestionAdapter
import com.molbulak.smartmoney.adapter.SelectQuestionListener
import com.molbulak.smartmoney.databinding.FragmentChooseBottomBinding
import com.molbulak.smartmoney.service.network.response.question.Question

class ChooseQuestionBF(
    private val questions: List<Question>,
    private val selectedQuestions: Question?,
    private val listener: SelectQuestionListener,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentChooseBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseBottomBinding.inflate(inflater, container, false)
        if (selectedQuestions != null) {
            binding.chooseRv.adapter =
                ChooseQuestionAdapter(questions, selectedQuestions, listener)
        } else {
            binding.chooseRv.adapter =
                ChooseQuestionAdapter(questions, selectedQuestions, listener)
        }
        binding.closeBtn.setOnClickListener { dismiss() }
        binding.chooseTitle.text = getString(R.string.choose_question)
        return binding.root
    }

}