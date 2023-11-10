package com.sampleandroidapp.domain.usecase

import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.repository.EmployeeRepository
import com.sampleandroidapp.domain.response.EmployeeResponse
import com.sampleandroidapp.domain.util.Result

class GetEmployees(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke(): Result<EmployeeResponse> = employeeRepository.employees()
}