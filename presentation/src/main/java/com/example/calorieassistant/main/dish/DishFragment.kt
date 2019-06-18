package com.example.calorieassistant.main.dish

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import com.example.domain.model.Ingredient
import kotlinx.android.synthetic.main.fragment_dish.*

class DishFragment: Fragment() {

    private lateinit var adapter: IngredientsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycler()
        fragment_dish_tv_name.text = "Курица запеченая в духовке"
    }

    private fun setupRecycler(){
        adapter = IngredientsListAdapter(requireContext())
        fragment_dish_rv_ingredients.layoutManager = LinearLayoutManager(requireContext())
        fragment_dish_rv_ingredients.adapter = adapter
        adapter.submitList(getIngredients())
    }

    private fun getIngredients(): List<Ingredient>{
        val list = ArrayList<Ingredient>()
        list.add(Ingredient("Курица", 195.0, 100.0))
        list.add(Ingredient("Соль", 0.0, 100.0))
        list.add(Ingredient("Майонез", 51.0, 15.0))
        list.add(Ingredient("Чеснок", 14.0, 7.0))
        list.add(Ingredient("Перец черный", 0.0, 3.0))
        list.add(Ingredient("Зелень", 15.0, 20.0))
        return list
    }
}