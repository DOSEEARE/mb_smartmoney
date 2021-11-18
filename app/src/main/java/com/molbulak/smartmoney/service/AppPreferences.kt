package com.molbulak.smartmoney.service

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {

    private const val NAME = ""
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    val apiKey = "Cq3Kry"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var token: String
        get() = preferences.getString("token", "")!!
        set(value) = preferences.edit {
            it.putString("token", value)
        }

    var currentCoordinate: String?
        get() = preferences.getString("currentCoordinate", " ")
        set(value) = preferences.edit {
            it.putString("currentCoordinate", value)
        }

    var userLogin: String
        get() = preferences.getString("userLogin", "")!!
        set(value) = preferences.edit {
            it.putString("userLogin", value)
        }

    var isLogined: Boolean
        get() = preferences.getBoolean("isAuthed", false)
        set(value) = preferences.edit {
            it.putBoolean("isAuthed", value)
        }
}