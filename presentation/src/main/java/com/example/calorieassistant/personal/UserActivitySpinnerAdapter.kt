package com.example.calorieassistant.personal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.domain.model.UserActivityLevel
import android.view.MotionEvent
import android.widget.TextView
import com.example.calorieassistant.R


class UserActivitySpinnerAdapter(context: Context,
                                 val res: Int,
                                 val array: List<com.example.domain.model.UserActivityLevel>,
                                 val listener: (com.example.domain.model.UserActivityLevel) -> Unit)
    : ArrayAdapter<com.example.domain.model.UserActivityLevel>(context, res, array){

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newView = convertView;
        if(newView == null){
            val inflater = LayoutInflater.from(context)
            newView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }
        val item = array[position]
        val text1 = newView?.findViewById(android.R.id.text1) as TextView
        text1.text = getText(item)
        setOnTouchListener(newView, item)
        return newView
    }

    private fun setOnTouchListener(view: View, item: com.example.domain.model.UserActivityLevel) {
        view.setOnTouchListener { v: View, e: MotionEvent ->
            listener(item)
            false
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newView = convertView
        if (newView == null) {
            val inflater = LayoutInflater.from(context)
            newView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        }
        (newView?.findViewById(android.R.id.text1) as TextView).text = getSelectedText(array[position])
        return newView
    }

    private fun getText(activityLevel: com.example.domain.model.UserActivityLevel): String{
        return when(activityLevel) {
            com.example.domain.model.UserActivityLevel.No -> context.resources.getString(R.string.user_activity_no)
            com.example.domain.model.UserActivityLevel.Low -> context.resources.getString(R.string.user_activity_low)
            com.example.domain.model.UserActivityLevel.Medium -> context.getString(R.string.user_activity_medium)
            com.example.domain.model.UserActivityLevel.High -> context.getString(R.string.user_activity_high)
            com.example.domain.model.UserActivityLevel.Extreme -> context.getString(R.string.user_activity_very_high)
        }
    }

    private fun getSelectedText(activityLevel: com.example.domain.model.UserActivityLevel): String{
        return when(activityLevel) {
            com.example.domain.model.UserActivityLevel.No -> "Умеренная"
            com.example.domain.model.UserActivityLevel.Low -> "Умеренная"
            com.example.domain.model.UserActivityLevel.Medium -> "Умеренная"
            com.example.domain.model.UserActivityLevel.High -> "Умеренная"
            com.example.domain.model.UserActivityLevel.Extreme -> "Умеренная"
        }
    }
}