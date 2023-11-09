package com.sampleandroidapp.data.api

import com.sampleandroidapp.domain.entities.BaseResponse
import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees")
    suspend fun getEmployees(): BaseResponse
}