package com.example.domain.model

import com.example.remote.model.Dish

class DishUsed(val dish: Dish, var weight: Double = 0.0){

    fun getName(): String{
        return dish.name
    }

    fun getCalories() = dish.getCalories()
}