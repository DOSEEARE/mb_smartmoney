package com.molbulak.smartmoney.service.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object EncryptedPreferences {
    private lateinit var preferences: SharedPreferences
    private val masterKeysAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    fun init(context: Context) {
        preferences = EncryptedSharedPreferences.create(
            "PreferencesFilename",
            masterKeysAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var password: String
        get() = preferences.getString("password", "")!!
        set(value) = preferences.edit {
            it.putString("password", value)
        }

    var login: String
        get() = preferences.getString("login", "")!!
        set(value) = preferences.edit {
            it.putString("login", value)
        }

}