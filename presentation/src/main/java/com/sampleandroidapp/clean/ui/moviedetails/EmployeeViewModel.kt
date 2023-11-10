package com.sampleandroidapp.clean.ui.moviedetails

import androidx.lifecycle.viewModelScope
import com.sampleandroidapp.clean.ui.base.BaseViewModel
import com.sampleandroidapp.clean.util.singleSharedFlow
import com.sampleandroidapp.data.util.DispatchersProvider
import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.entities.EmployeeEntity
import com.sampleandroidapp.domain.response.EmployeeResponse
import com.sampleandroidapp.domain.usecase.GetEmployees
import com.sampleandroidapp.domain.util.Result
import com.sampleandroidapp.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val getEmployees: GetEmployees,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    private val _employeeState: MutableSharedFlow<List<EmployeeEntity>> = singleSharedFlow()
    val employeeState = _employeeState.asSharedFlow()
    private suspend fun getEmployees(){
        val result:Result<EmployeeResponse> = getEmployees.invoke()
        result.onSuccess { _employeeState.tryEmit(it.data) }
    }

    init {
        launchOnMainImmediate {
            getEmployees()
        }
    }
}