package com.example.shared

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager (val context: Context){

    companion object{
        const val USER_NAME = "USER_NAME"
    }

    private val APP_SHARED_PREFERENCES = "app_shared_preferences"
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun saveString(key: String, text: String?): Boolean{
        return sharedPreferences.edit().putString(key, text).commit()
    }

    fun getString(key: String): String?{
        return sharedPreferences.getString(key, null)
    }
}