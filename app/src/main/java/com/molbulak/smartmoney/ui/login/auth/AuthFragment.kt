package com.molbulak.smartmoney.ui.login.auth

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molbulak.smartmoney.App
import com.molbulak.smartmoney.R
import com.molbulak.smartmoney.Screens
import com.molbulak.smartmoney.adapter.SelectGenderListener
import com.molbulak.smartmoney.adapter.SelectNationListener
import com.molbulak.smartmoney.adapter.SelectQuestionListener
import com.molbulak.smartmoney.databinding.FragmentAuthBinding
import com.molbulak.smartmoney.extensions.toast
import com.molbulak.smartmoney.service.network.Status
import com.molbulak.smartmoney.service.network.body.AuthBody
import com.molbulak.smartmoney.service.network.response.country.Country
import com.molbulak.smartmoney.service.network.response.gender.Gender
import com.molbulak.smartmoney.service.network.response.nationality.Nation
import com.molbulak.smartmoney.service.network.response.question.Question
import com.molbulak.smartmoney.ui.login.LoginViewModel
import com.molbulak.smartmoney.util.Date
import com.molbulak.smartmoney.util.MyUtil
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment(val country: Country, val numberPhone: String) :
    Fragment(), SelectQuestionListener, SelectNationListener,
    SelectGenderListener, SelectDateListener {
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

    private var selectedDate: Date? = null

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
        initViews()
        initLists()
    }

    private fun initViews() {
        binding.numberPhoneOne.setText(numberPhone)
        binding.numberPhoneTwo.inputType = InputType.TYPE_CLASS_PHONE
        binding.registeredTv.setOnClickListener {
            App.getRouter().newRootScreen(Screens.LoginScreen())
        }
        binding.authBtn.setOnClickListener {
            if (checkFields()) {
                auth()
            }
        }
        binding.bornDate.setOnClickListener {
            ChooseDateBF(selectedDate, this).show(childFragmentManager, "ChooseDateBF")
        }
        val inputMask = MyUtil.inputMask(country.phone_mask)
        inputMask.installOn(binding.numberPhoneTwo)
    }

    private fun checkFields(): Boolean {
        var success: Boolean
        val views = binding.run {
            listOf(nameEt,
                surnameEt,
                genderDb,
                bornDate,
                nationalityDb,
                numberPhoneOne,
                questionDb,
                answerQuestion)
        }
        views.forEach {
            if (it.text.isNullOrBlank()) {
                it.error = getString(R.string.warning_empty)
                success = false
            }
        }
        success = binding.agreeCb.isChecked
        return success
    }

    private fun initLists() {
        viewModel.gender().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    listGender = it.data?.result!!
                    binding.genderDb.setOnClickListener {
                        chooseGenderBF = ChooseGenderBF(listGender, selectedGender, this)
                        chooseGenderBF.show(childFragmentManager, "ChooseGenderBF")
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
                        chooseNationBF = ChooseNationBF(listNationality, selectedNation, this)
                        chooseNationBF.show(childFragmentManager, "ChooseNationBF")
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
                    binding.questionDb.setOnClickListener {
                        chooseQuestionBF = ChooseQuestionBF(listQuestion, selectedQuestion, this)
                        chooseQuestionBF.show(childFragmentManager, "ChooseQuestionBF")
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

    private fun auth() {
        val name = binding.nameEt.text.toString()
        val surName = binding.surnameEt.text.toString()
        val secondName = binding.secondNameEt.text.toString()
        val bornDate = binding.bornDate.text.toString()
        val numberOne = binding.numberPhoneOne.text.toString()
        val numberTwo = binding.numberPhoneTwo.text.toString()
        val answer = binding.answerQuestion.text.toString()

        val authBody = AuthBody(
            first_name = name,
            last_name = surName,
            system = "1",
            first_phone = MyUtil.onlyDigits(numberOne),
            gender = selectedGender!!.id,
            nationality = selectedNation!!.id,
            question = selectedQuestion!!.id,
            response = answer,
            second_name = secondName,
            second_phone = MyUtil.onlyDigits(numberTwo),
            u_date = bornDate,
            sms_code = "312"
        )

        viewModel.auth(authBody).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    toast("Успешно регистрация ${it.data!!.result?.code}")
                }
                Status.ERROR -> {
                    toast("Ошибка регистрация ${it.data!!.error?.code}")
                }
                Status.NETWORK -> {
                    toast("Проблемы с подключением")
                }
            }
        })
    }

    override fun genderSelected(gender: Gender) {
        selectedGender = gender
        binding.genderDb.setText(gender.name)
        chooseGenderBF.dismiss()
    }

    override fun nationSelected(nation: Nation) {
        selectedNation = nation
        binding.nationalityDb.setText(nation.name)
        chooseNationBF.dismiss()
    }

    override fun questionSelected(question: Question) {
        selectedQuestion = question
        binding.questionDb.setText(question.name)
        chooseQuestionBF.dismiss()
    }

    override fun dateSelected(date: Date) {
        selectedDate = date
        binding.bornDate.setText(date.getDate())
    }

}