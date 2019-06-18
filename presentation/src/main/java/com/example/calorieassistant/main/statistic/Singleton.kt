package com.example.calorieassistant.main.statistic

import com.example.calorieassistant.App
import com.example.domain.model.DishUsed
import com.example.domain.model.Sex
import com.example.shared.SharedPreferenceManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Singleton{

    val listDishesSelected = ArrayList<com.example.domain.model.DishUsed>()

    fun getFormula(): Single<Double> {
        return App.getInstance()
            .database
            .userDao()
            .get(App.getInstance().sharedPreferanceManager.getString(SharedPreferenceManager.USER_NAME)!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                val calories = when(it.sex){
                    Sex.Man -> 88.362 + (13.397 * it.weight) + (4.799 * it.height) - (5.677 * it.age)
                    Sex.Woman -> 447.593 + (9.247 * it.weight) + (3.098 * it.height) - (4.330 * it.age)
                }
                calories * it.activityLevel.coefficient
            }
    }

    fun getDailyHavka(): Double{
        var poel = 0.0
        for(dish in listDishesSelected){
            poel += dish.getCalories() * dish.weight
        }
        return poel / 100
    }
}