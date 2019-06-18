package com.example.calorieassistant.main.statistic.daily

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.calorieassistant.R
import com.example.calorieassistant.databinding.FragmentDailyStatisticBinding
import com.example.calorieassistant.main.statistic.Singleton
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_daily_statistic.*

class DailyStatisticFragment : Fragment() {

    private lateinit var viewModel: DailyStatisticViewModel
    private lateinit var adapter: DailyStatisticDishesListAdapter
    private lateinit var binding: FragmentDailyStatisticBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(DailyStatisticViewModel::class.java)
        subscribeOpenChooserFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_statistic, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupBindings()
    }

    private fun setupBindings() {
        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        fragment_daily_statistic_rv_dishes.layoutManager = LinearLayoutManager(requireContext())
        DailyStatisticDishesListAdapter(requireContext()) { dish -> viewModel.onDishClicked(dish) }
            .also {
                adapter = it
                fragment_daily_statistic_rv_dishes.adapter = it
            }
        adapter.submitList(Singleton.listDishesSelected)
        Singleton.getFormula().doOnSuccess {
            fragment_daily_statistic_tv_daily_rate.text = (it - Singleton.getDailyHavka()).toInt().toString()
        }.subscribe()

    }

    private fun subscribeOpenChooserFragment() {
        viewModel.addDishLiveData
            .observe(this,
                Observer {
                    it?.let {
                        if (it) Navigation.findNavController(binding.view).navigate(R.id.dishChooserFragment)
                    }
                })

    }
}
