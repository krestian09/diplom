package com.example.remote.model

import com.google.gson.annotations.Expose

data class Dish(@Expose val id: Long,
                @Expose val name: String,
                @Expose val calories: Double?,
                @Expose val dishIngredients: Array<DishIngredient>?) {

    fun getCalories(): Double{
        calories?.let { return it }
        var cal = 0.0
        dishIngredients?.let {
            for(dish in dishIngredients){ cal += dish.getCalores() * dish.weight
            }
        }
        return cal / 1000
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dish

        if (id != other.id) return false
        if (name != other.name) return false
        if (calories != other.calories) return false
        if (dishIngredients != null) {
            if (other.dishIngredients == null) return false
            if (!dishIngredients.contentEquals(other.dishIngredients)) return false
        } else if (other.dishIngredients != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (calories?.hashCode() ?: 0)
        result = 31 * result + (dishIngredients?.contentHashCode() ?: 0)
        return result
    }

}