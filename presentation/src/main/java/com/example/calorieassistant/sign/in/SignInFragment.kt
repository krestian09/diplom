package com.example.calorieassistant.sign.`in`

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calorieassistant.R
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.calorieassistant.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*
import androidx.navigation.NavOptions




class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        subscribeAuthorizationResult()
    }

    private fun subscribeAuthorizationResult(){
        viewModel.liveDataAuthorizationCompleted.observe(this, Observer{startMain()})
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(fragment_sign_in_et_login)
        setupViewModel()
        setupBindings()
        setupButtonListeners()
    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
    }

    private fun setupBindings() {
        val user = com.example.domain.model.UserAuthorization("", "")
        binding.userAuthorization = user
        viewModel.setUser(user)
    }

    private fun setupButtonListeners() {
        fragment_sign_in_btn_sign_in.setOnClickListener {
            subscribeSignInResult(viewModel.onButtonLoginClicked())
        }
        fragment_sign_in_btn_sign_up.setOnClickListener {
            navController.navigate(R.id.signUpFragment)
        }
    }

    private fun subscribeSignInResult(liveData: LiveData<Boolean>) {
        liveData.observe(this, Observer {
            it?.let {
                if (it) { startMain() }
                else Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun startMain(){
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.signInFragment, true)
            .build()
        navController.navigate(R.id.mainFragment, null, navOptions)
    }

}