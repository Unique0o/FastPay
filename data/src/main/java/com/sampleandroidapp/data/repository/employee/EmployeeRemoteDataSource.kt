package com.sampleandroidapp.data.repository.employee

import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.response.EmployeeResponse
import com.sampleandroidapp.domain.util.Result

interface EmployeeRemoteDataSource {
    suspend fun getEmployees(): Result<EmployeeResponse>
}