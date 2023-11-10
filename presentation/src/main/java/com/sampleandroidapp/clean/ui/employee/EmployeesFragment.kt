package com.sampleandroidapp.clean.ui.employee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.sampleandroidapp.clean.databinding.FragmentEmployeesBinding
import com.sampleandroidapp.clean.ui.base.BaseFragment
import com.sampleandroidapp.clean.ui.moviedetails.EmployeeViewModel
import com.sampleandroidapp.clean.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeesFragment : BaseFragment<FragmentEmployeesBinding>() {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentEmployeesBinding = FragmentEmployeesBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
    }

    private fun observeViewModel() = with(viewModel) {
        launchAndRepeatWithViewLifecycle {
            launch { employeeState.collect { Log.e(EmployeesFragment::class.java.simpleName, "ListEmployee size = ${it.size}") } }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmployeesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}