package com.example.calorieassistant.main.dishes

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import com.example.domain.model.Dish
import kotlinx.android.synthetic.main.card_dish.view.*


class DishesListAdapter(val listener: (com.example.domain.model.Dish) -> Unit)
    : ListAdapter<com.example.domain.model.Dish, DishesListAdapter.DishesViewHolder>(DishesDiffUtilCallback()){

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): DishesViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.card_dish, container, false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    inner class DishesViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(dish: com.example.domain.model.Dish){
            itemView.card_dish_tv_name.text = dish.name
            itemView.card_dish_tv_calories.text = dish.calories.toString()
            itemView.setOnClickListener { listener(dish) }
            itemView.card_dish_b_more_info.setOnClickListener { listener(dish) }
        }
    }

    class DishesDiffUtilCallback: DiffUtil.ItemCallback<com.example.domain.model.Dish>(){

        override fun areItemsTheSame(new: com.example.domain.model.Dish, old: com.example.domain.model.Dish): Boolean {
            return new.name == old.name
        }

        override fun areContentsTheSame(new: com.example.domain.model.Dish, old: com.example.domain.model.Dish): Boolean {
            return new.calories == old.calories
        }

    }
}