package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.databinding.FragmentChooseDateBinding
import com.molbulak.smartmoney.util.Date
import java.util.*

class ChooseDateBF(
    private val selectedDate: Date?,
    private val listener: SelectDateListener,
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentChooseDateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChooseDateBinding.inflate(inflater, container, false)

        binding.run {
            if (selectedDate != null) {
                chooseDate.updateDate(
                    selectedDate.year,
                    selectedDate.month,
                    selectedDate.day)
            } else {
                val currentDate = Calendar.getInstance()
                binding.chooseDate.maxDate = currentDate.time.time
                binding.chooseDate.updateDate(
                    currentDate.get(Calendar.YEAR) - 18,
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )
            }
        }
        binding.acceptBtn.setOnClickListener {
            val day = binding.chooseDate.dayOfMonth
            val month = binding.chooseDate.month + 1
            val year = binding.chooseDate.year
            val date = Date(day, month, year)
            listener.dateSelected(date)
            dismiss()
        }
        return binding.root
    }

}

interface SelectDateListener {
    fun dateSelected(date: Date)
}