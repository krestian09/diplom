package com.example.calorieassistant.main.dishes.collections

import kotlinx.android.synthetic.main.card_dishes_collection.view.*

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import com.example.domain.model.Dish


class DishesCollectionsListAdapter(val listener: (String) -> Unit) :
    ListAdapter<String, DishesCollectionsListAdapter.DishesCollectionsViewHolder>(DishesDiffUtilCallback()) {

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): DishesCollectionsViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_dishes_collection, container, false)
        return DishesCollectionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesCollectionsViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    inner class DishesCollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dish: String) {
            itemView.card_dishes_collections_tv_name.text = dish
            itemView.setOnClickListener { listener(dish) }
        }
    }

    class DishesDiffUtilCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(new: String, old: String): Boolean {
            return new == old
        }

        override fun areContentsTheSame(new: String, old: String): Boolean {
            return true
        }
    }
}