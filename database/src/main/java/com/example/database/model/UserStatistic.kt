package com.example.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.domain.model.Dish
import com.example.domain.model.DishUsed

@Entity
data class UserStatistic(@PrimaryKey(autoGenerate = true) val id: Long,
                         val name: String,
                         val dishes: List<DishUsed>,
                         val timestamp: Long)