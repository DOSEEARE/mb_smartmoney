package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.adapter.SelectGenderListener
import com.molbulak.smartmoney.adapter.SelectNationListener
import com.molbulak.smartmoney.adapter.SelectQuestionListener
import com.molbulak.smartmoney.databinding.FragmentAuthBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.response.gender.Gender
import com.molbulak.smartmoney.service.network.response.nationality.Nation
import com.molbulak.smartmoney.service.network.response.question.Question
import com.molbulak.smartmoney.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment(val authId: Int) : Fragment(), SelectQuestionListener, SelectNationListener,
    SelectGenderListener {
    private lateinit var binding: FragmentAuthBinding
    private val viewModel: LoginViewModel by viewModel()

    private var listGender = listOf<Gender>()
    private var selectedGender: Gender? = null
    private lateinit var chooseGenderBF: ChooseGenderBF

    private var listNationality = listOf<Nation>()
    private var selectedNation: Nation? = null
    private lateinit var chooseNationBF: ChooseNationBF

    private var listQuestion = listOf<Question>()
    private var selectedQuestion: Question? = null
    private lateinit var chooseQuestionBF: ChooseQuestionBF

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        chooseGenderBF = ChooseGenderBF(listGender, null, this)
        chooseNationBF = ChooseNationBF(listNationality, null, this)
        chooseQuestionBF = ChooseQuestionBF(listQuestion, null, this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener { App.getRouter().exit() }
        initLists()
        initViews()
    }

    private fun initViews() {

    }

    private fun initLists() {
        viewModel.gender().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    listGender = it.data?.result!!
                    binding.genderDb.setOnClickListener {
                        ChooseGenderBF(listGender, selectedGender, this)
                    }
                }
                Status.ERROR -> {
                    toast("Ошибка пол ${it.data!!.error?.code}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }
        })

        viewModel.nationality().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    listNationality = it.data?.result!!
                    binding.nationalityDb.setOnClickListener {
                        ChooseNationBF(listNationality, selectedNation, this)
                    }
                }
                Status.ERROR -> {
                    toast("Ошибка национальность ${it.data!!.error?.code}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }
        })

        viewModel.question().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    listQuestion = it.data?.result!!
                    binding.genderDb.setOnClickListener {
                        ChooseQuestionBF(listQuestion, selectedQuestion, this)
                    }
                }
                Status.ERROR -> {
                    toast("Ошибка вопросы ${it.data!!.error.code}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    override fun genderSelected(gender: Gender) {
        selectedGender = gender
    }

    override fun nationSelected(nation: Nation) {
        selectedNation = nation
    }

    override fun questionSelected(question: Question) {
        selectedQuestion = question
    }

}