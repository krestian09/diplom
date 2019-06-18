package com.example.calorieassistant.main.statistic.daily

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.domain.model.Dish
import com.example.domain.model.DishUsed

class DailyStatisticViewModel : ViewModel(){

    val addDishLiveData = MutableLiveData<Boolean>()

    fun onDishClicked(dish: com.example.domain.model.DishUsed){

    }

    fun onAddDishClicked(){
        addDishLiveData.value = true
        addDishLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }
}