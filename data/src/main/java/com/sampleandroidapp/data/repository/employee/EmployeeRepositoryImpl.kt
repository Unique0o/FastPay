package com.sampleandroidapp.data.repository.employee

import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.repository.EmployeeRepository
import com.sampleandroidapp.domain.util.Result
import com.sampleandroidapp.domain.util.getResult

class EmployeeRepositoryImpl
    constructor(
        private val remote: EmployeeRemoteDataSource,
    ): EmployeeRepository {
    override suspend fun employees(): Result<BaseResponse> = remote.getEmployees().getResult({
        it
    }, {
        remote.getEmployees()
    })

}