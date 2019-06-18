package com.example.calorieassistant.personal

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.calorieassistant.R
import com.example.domain.model.Sex

class SexSpinnerAdapter(
    context: Context,
    val res: Int,
    val array: List<com.example.domain.model.Sex>,
    val listener: (com.example.domain.model.Sex) -> Unit
) : ArrayAdapter<com.example.domain.model.Sex>(context, res, array) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newView = convertView
        if (newView == null) {
            val inflater = LayoutInflater.from(context)
            newView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }
        val item = array[position]
        val text1 = newView?.findViewById(android.R.id.text1) as TextView
        text1.text = getText(item)
        setOnTouchListener(newView, item)
        return newView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newView = convertView
        if (newView == null) {
            val inflater = LayoutInflater.from(context)
            newView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }
        (newView?.findViewById(android.R.id.text1) as TextView).text = getText(array[position])
        return newView
    }

    private fun setOnTouchListener(view: View, item: com.example.domain.model.Sex) {
        view.setOnTouchListener { v: View, e: MotionEvent ->
            listener(item)
            false
        }
    }

    private fun getText(activityLevel: com.example.domain.model.Sex): String {
        return when (activityLevel) {
            com.example.domain.model.Sex.Man -> context.getString(R.string.sex_man)
            com.example.domain.model.Sex.Woman -> context.getString(R.string.sex_woman)
        }
    }
}