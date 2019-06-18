package com.example.calorieassistant.personal


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.calorieassistant.R
import com.example.calorieassistant.databinding.FragmentPersonalInfoRegistrationBinding
import com.example.domain.model.Sex
import com.example.domain.model.UserActivityLevel
import com.example.domain.model.UserPersonal
import kotlinx.android.synthetic.main.fragment_personal_info_registration.*

class PersonalInfoRegistration : Fragment() {

    private lateinit var viewModel: PersonalInfoRegistrationViewModel
    private lateinit var binding: FragmentPersonalInfoRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_personal_info_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupBindings()
        setupSpinners()
        setupButtonListeners()
        setupSubscribes()
        setupTestViewListeners()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this)
            .get(PersonalInfoRegistrationViewModel::class.java)
    }

    private fun setupBindings() {
        val userPersonal = UserPersonal()
        binding.userPersonal = userPersonal
        viewModel.setUserPersonal(userPersonal)
    }

    private fun setupSpinners() {
        fragment_personal_info_spn_sex.adapter = SexSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Sex.getAll()
        ) { viewModel.onUserSexChanged(it)}

        fragment_personal_info_spn_activity.adapter = UserActivitySpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            UserActivityLevel.getAll()
        ) { viewModel.onUserActivityLevelChanged(it) }
    }

    private fun setupButtonListeners(){
        fragment_personal_info_btn_submit.setOnClickListener { viewModel.onButtonSubmitClicked() }
    }

    private fun setupTestViewListeners(){
        fragment_personal_info_et_height.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onHeightChanged( s?.toString()?.toLongOrNull() ?: 0)
            }
        })

        fragment_personal_info_et_weight.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onWeightChanged( s?.toString()?.toLongOrNull() ?: 0)
            }
        })

        fragment_personal_info_age.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onAgeChanged( s?.toString()?.toIntOrNull() ?: 0)
            }
        })
    }

    private fun setupSubscribes(){
        viewModel.submitData.observe(this, Observer {
            it?.let {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
                return@Observer
            }
            startMainFragment()
        })
    }

    private fun startMainFragment(){
        Navigation.findNavController(view!!).navigate(R.id.mainFragment)
    }
}