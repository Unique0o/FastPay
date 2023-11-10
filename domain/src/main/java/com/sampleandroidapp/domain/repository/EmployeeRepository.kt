package com.sampleandroidapp.domain.repository

import com.sampleandroidapp.domain.response.EmployeeResponse
import com.sampleandroidapp.domain.util.Result
interface EmployeeRepository {
    suspend fun employees(): Result<EmployeeResponse>
}