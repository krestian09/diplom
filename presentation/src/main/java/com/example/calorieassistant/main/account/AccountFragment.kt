package com.example.calorieassistant.main.account

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.calorieassistant.R
import com.example.calorieassistant.main.MainFragment
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_personal_info_registration.*

class AccountFragment : Fragment() {

    private lateinit var binding: com.example.calorieassistant.databinding.FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        subscribeLogOut()
        subsribeEditableStatus()
    }

    private fun subsribeEditableStatus() {
        viewModel.isEditableLiveData.observe(this,
            Observer { status: Boolean? -> requireActivity().invalidateOptionsMenu() })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTestViewListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_edit_submit, menu)
        this.menu = menu
        menu?.findItem(R.id.menu_item_edit)?.setOnMenuItemClickListener { viewModel.onEditClicked(); true }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        if (viewModel.editable.get()) menu?.findItem(R.id.menu_item_edit)?.title = getString(R.string.submit)
        else menu?.findItem(R.id.menu_item_edit)?.title = getString(R.string.edit)
        super.onPrepareOptionsMenu(menu)
    }

    private fun setupTestViewListeners(){
        fragment_account_tv_height.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onHeightChanged( s?.toString()?.toLongOrNull() ?: 0)
            }
        })

        fragment_account_tv_weight.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onWeightChanged( s?.toString()?.toLongOrNull() ?: 0)
            }
        })

        fragment_account_tv_age.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onAgeChanged( s?.toString()?.toIntOrNull() ?: 0)
            }
        })
    }

    private fun subscribeLogOut() {
        viewModel.logOutLiveData.observe(this, Observer { status: Boolean? ->
            status?.let {
                if (it) navigateToLogIn()
            }
        })
    }

    private fun navigateToLogIn() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.mainFragment, true)
            .build()
        Navigation.findNavController(requireActivity(), R.id.activity_main_nav_host_fragment)
            .navigate(R.id.signInFragment, null, navOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        menu?.clear()
    }
}
