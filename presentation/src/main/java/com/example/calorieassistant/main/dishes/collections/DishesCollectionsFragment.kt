package com.example.calorieassistant.main.dishes.collections

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.calorieassistant.R
import kotlinx.android.synthetic.main.fragment_dishes_collections.*

class DishesCollectionsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dishes_collections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DishesCollectionsListAdapter {
            openDishesFragment()
        }.let {
            fragment_dishes_collections_rv_collections.adapter = it
            it.submitList(getCollections())
        }
    }

    private fun getCollections(): List<String> {
        val collections = ArrayList<String>()
        collections.add("Мясо")
        collections.add("Рыба")
        collections.add("Напитки")
        collections.add("Фрукты")
        collections.add("Хлебобулочные")
        collections.add("Молочные продукты")
        return collections
    }

    private fun openDishesFragment() {
        Navigation.findNavController(fragment_dishes_collections_rv_collections).navigate(R.id.dishesFragment)
    }
}