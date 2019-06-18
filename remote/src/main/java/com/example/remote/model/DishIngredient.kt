package com.example.remote.model

import com.google.gson.annotations.Expose

data class DishIngredient(@Expose val id: Long, @Expose val dishIngredient: Dish, @Expose val weight: Double){

    fun getCalores(): Double{
        return dishIngredient.calories ?: 0.0
    }
}