package com.example.calorieassistant.main.statistic.daily

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import com.example.domain.model.Dish
import com.example.domain.model.DishUsed
import kotlinx.android.synthetic.main.card_dish.view.*


class DailyStatisticDishesListAdapter(val context: Context,
    val listener: (com.example.domain.model.DishUsed) -> Unit)
    : ListAdapter<com.example.domain.model.DishUsed, DailyStatisticDishesListAdapter.DishesViewHolder>(DishesDiffUtilCallback()){


    override fun onCreateViewHolder(container: ViewGroup, pos: Int): DishesViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.card_dish, container, false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    inner class DishesViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(dish: com.example.domain.model.DishUsed){
            itemView.card_dish_tv_name.text = dish.getName()
            "${context.getString(R.string.calories)}: ${dish.getCalories().toInt()}".let {
                itemView.card_dish_tv_calories.text = it
            }
            itemView.setOnClickListener { listener(dish) }
            itemView.card_dish_b_more_info.setOnClickListener { listener(dish) }
            "${context.getString(R.string.weight)}:  ${dish.weight.toInt()}".let {
                itemView.card_dish_tv_weight.text = it
            }
        }
    }

    class DishesDiffUtilCallback: DiffUtil.ItemCallback<com.example.domain.model.DishUsed>(){

        override fun areItemsTheSame(new: com.example.domain.model.DishUsed, old: com.example.domain.model.DishUsed): Boolean {
            return new.getName() == old.getName()
        }

        override fun areContentsTheSame(new: com.example.domain.model.DishUsed, old: com.example.domain.model.DishUsed): Boolean {
            return new.getCalories() == old.getCalories()
        }

    }
}