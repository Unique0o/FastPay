package com.sampleandroidapp.domain.repository

import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.util.Result
interface EmployeeRepository {
    suspend fun employees(): Result<BaseResponse>
}