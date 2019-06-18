package com.example.calorieassistant.main.chooser

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import com.example.domain.model.DishUsed
import kotlinx.android.synthetic.main.card_dish_chooser.view.*



class DishChooserListAdapter(val context: Context,
                             val clickListener: (com.example.domain.model.DishUsed) -> Unit,
                             val clearListener: (com.example.domain.model.DishUsed) -> Unit) :
    ListAdapter<com.example.domain.model.DishUsed, DishChooserListAdapter.DishesViewHolder>(DishesDiffUtilCallback()) {

    override fun submitList(list: List<com.example.domain.model.DishUsed>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): DishesViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_dish_chooser, container, false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, pos: Int) {
        holder.bind(getItem(pos))
    }

    inner class DishesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dish: com.example.domain.model.DishUsed) {
            itemView.card_dish_chooser_tv_name.text = dish.getName()
            "${context.getString(R.string.calories)} ${dish.getCalories().toInt()}".let {
                itemView.card_dish_chooser_tv_calories.text = it
            }
            itemView.card_dish_chooser_b_close.setOnClickListener { clearListener(dish) }
            itemView.setOnClickListener { clickListener(dish) }
            itemView.card_dish_chooser_b_more_info.setOnClickListener { clickListener(dish) }
            itemView.card_dish_chooser_et_weight.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    dish.weight = s.toString().toDoubleOrNull() ?: 0.0
                }
            })
        }
    }

    class DishesDiffUtilCallback : DiffUtil.ItemCallback<com.example.domain.model.DishUsed>() {

        override fun areItemsTheSame(new: com.example.domain.model.DishUsed, old: com.example.domain.model.DishUsed): Boolean {
            return false
        }

        override fun areContentsTheSame(new: com.example.domain.model.DishUsed, old: com.example.domain.model.DishUsed): Boolean {
            return false
        }

    }
}