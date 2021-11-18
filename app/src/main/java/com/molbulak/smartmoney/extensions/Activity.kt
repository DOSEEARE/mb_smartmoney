package com.molbulak.smartmoney.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun <T : AppCompatActivity> Fragment.parentActivity() = requireActivity() as T
