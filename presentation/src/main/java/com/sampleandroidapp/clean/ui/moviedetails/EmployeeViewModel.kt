package com.sampleandroidapp.clean.ui.moviedetails

import com.sampleandroidapp.clean.ui.base.BaseViewModel
import com.sampleandroidapp.data.util.DispatchersProvider
import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.usecase.GetEmployees
import com.sampleandroidapp.domain.util.Result
import javax.inject.Inject

class EmployeeViewModel @Inject constructor(
    private val getEmployees: GetEmployees,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {
    /*data class EmployeeUiState(
        val id: Int = -1,
        val employee_name: String = "",
        val empyee_salary: Double = 0.00,
        val employee_age: Int = 0,
        val profile_image: String = ""
    )

    private val _uiState: MutableStateFlow<EmployeeUiState> = MutableStateFlow(
        EmployeeUiState()
    )
    val uiState = _uiState.asStateFlow()*/
    private suspend fun getEmployees(): Result<BaseResponse> = getEmployees.invoke()
}