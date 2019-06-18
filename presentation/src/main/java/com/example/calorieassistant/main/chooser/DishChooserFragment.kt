package com.example.calorieassistant.main.chooser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.navigation.Navigation
import com.example.calorieassistant.MainActivity
import com.example.calorieassistant.R
import com.example.domain.model.DishUsed
import com.example.remote.model.Dish
import kotlinx.android.synthetic.main.fragment_dish_chooser.*

class DishChooserFragment : Fragment() {

    private lateinit var viewModel: DishChooserViewModel
    private lateinit var listAdapter: DishChooserListAdapter
    private var menuItemSubmit: MenuItem? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(DishChooserViewModel::class.java)
        subscribeDishesChanges()
        subscribeDishesSelectedChanges()
    }

    private fun subscribeDishesChanges() {
        viewModel.dishesLoaded.observe(this, Observer {
            it?.let { setDishesToAutoCompleteTextView(it) }
        })
    }

    private fun subscribeDishesSelectedChanges() {
        viewModel.dishesSelected.observe(this, Observer { list: List<com.example.domain.model.DishUsed>? ->
            list?.let { listAdapter.submitList(it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dish_chooser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDishChooserAutoComplete()
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        this.menu = menu
        menuItemSubmit = menu?.add("Submit")
        menuItemSubmit?.setIcon(R.drawable.ic_check_24dp)
        menuItemSubmit?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItemSubmit?.setOnMenuItemClickListener { viewModel.onButtonSubmitClicked();
            navigateToBack()
            true }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun navigateToBack(){
        requireActivity().onBackPressed()
    }

    private fun setupDishChooserAutoComplete() {
        fragment_dish_chooser_auto_complete_chooser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchedDish.onNext(s.toString())
            }
        })
    }

    private fun setDishesToAutoCompleteTextView(dishes: List<Dish>) {
        ChooserAutoCompleteAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, dishes) {
            viewModel.onDishSelected(it)
            fragment_dish_chooser_auto_complete_chooser.dismissDropDown()
        }.let {
            fragment_dish_chooser_auto_complete_chooser.setAdapter(it)
            fragment_dish_chooser_auto_complete_chooser.showDropDown()
        }
    }

    private fun setupRecyclerView() {
        fragment_dish_chooser_rv_dishes.layoutManager = LinearLayoutManager(requireContext())
        DishChooserListAdapter(requireContext(),
            { viewModel.onDishMoreInfoClicked(it) },
            { viewModel.onDishRemoveClicked(it) }).let {
            listAdapter = it
            fragment_dish_chooser_rv_dishes.adapter = it
        }
    }

    override fun onStop() {
        super.onStop()
        menu?.clear()
    }
}