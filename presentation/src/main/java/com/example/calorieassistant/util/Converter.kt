package com.example.calorieassistant.util

import android.databinding.InverseMethod
import com.example.domain.model.Sex


object Converter {

    @InverseMethod("convertStringToDouble")
    @JvmStatic
    fun convertDoubleToString(
        value: Double
    ): String {
        return value.toString()
    }

    @InverseMethod("convertStringToInt")
    @JvmStatic
    fun convertIntToString(value: Int): String{
        return value.toString()
    }

    @JvmStatic
    fun convertStringToDouble(
        value: String
    ): Double {
        return value.toDoubleOrNull() ?: 0.0
    }

    @JvmStatic
    fun convertStringToInt(value: String): Int {
        return value.toIntOrNull() ?: 0
    }
}