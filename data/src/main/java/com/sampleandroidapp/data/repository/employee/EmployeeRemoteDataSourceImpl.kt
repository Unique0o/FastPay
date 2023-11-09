package com.sampleandroidapp.data.repository.employee

import com.sampleandroidapp.data.api.EmployeeApi
import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.util.Result

class EmployeeRemoteDataSourceImpl(
    private val api: EmployeeApi
): EmployeeRemoteDataSource {
    override suspend fun getEmployees(): Result<BaseResponse> = try {
        val result = api.getEmployees()
        Result.Success(result)
    } catch (e: Exception) {
        Result.Error(e)
    }
}