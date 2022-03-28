package com.example.halanchallenge.data.storage.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private lateinit var sharedPreferences: SharedPreferences
        private const val SHARED_NAME = "Halan"
        private const val TOKEN = "TOKEN"

    }

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }

    var token: String?
        get() {
            return sharedPreferences.getString(TOKEN, "")
        }
        set(value) = sharedPreferences.edit().putString(TOKEN, value).apply()

    fun clearPreferences() {
        token = ""
    }

}