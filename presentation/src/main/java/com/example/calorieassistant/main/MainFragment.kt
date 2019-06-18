package com.example.calorieassistant.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.calorieassistant.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(){

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationController()
    }

    private fun setNavigationController(){
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_main_nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    fun navigateToSignIn(){

    }
}