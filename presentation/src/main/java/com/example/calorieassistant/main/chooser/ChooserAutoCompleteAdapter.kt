package com.example.calorieassistant.main.chooser

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.calorieassistant.R
import com.example.remote.model.Dish



class ChooserAutoCompleteAdapter(context: Context,
                                 val res: Int,
                                 val array: List<Dish>,
                                 val listener: (Dish) -> Unit)
    : ArrayAdapter<Dish>(context, res, array){

    override fun getCount() = array.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(context)
                .inflate(R.layout.layout_search_dish_chooser, parent, false)
        }
        val item = array[position]
        view?.findViewById<TextView>(R.id.layout_search_dish_chooser_tv_name)?.text = item.name
        view?.findViewById<TextView>(R.id.layout_search_dish_chooser_tv_calorie)?.text = item.getCalories().toInt().toString()
        setOnTouchListener(view!!, item)
        return view
    }

    private fun setOnTouchListener(view: View, item: Dish) {
        view.setOnTouchListener { v: View, e: MotionEvent ->
            listener(item)
            false
        }
    }

    override fun getFilter(): Filter{
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.count = array.size
                results.values = array
                return results
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return ""
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            }
        }
    }
}