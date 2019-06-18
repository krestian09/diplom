package com.example.calorieassistant.sign.up

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.calorieassistant.R
import com.example.calorieassistant.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment: Fragment(){
    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(fragment_sign_up_et_login)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        setupBindings()
        setupButtonListeners()
    }

    private fun setupBindings() {
        val user = com.example.domain.model.UserAuthorization("", "")
        binding.userAuthorization = user
        viewModel.setUser(user)
    }

    private fun setupButtonListeners() {
        fragment_sign_up_btn_sign_in.setOnClickListener {
            subscribeSignInResult(viewModel.onButtonSignUpClicked())
        }
    }

    private fun subscribeSignInResult(liveData: LiveData<Boolean>) {
        liveData.observe(this, Observer {
            it?.let {
                if (it) navController.navigate(R.id.personalInfoRegistration)
                else Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
        })
    }
}