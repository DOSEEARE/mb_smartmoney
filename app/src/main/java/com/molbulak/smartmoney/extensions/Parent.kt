package com.molbulak.smartmoney.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun <T : AppCompatActivity> Fragment.parentActivity() = requireActivity() as T

fun <T : Fragment> Fragment.parentFragment() = parentFragment as T
