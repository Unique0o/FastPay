package com.sampleandroidapp.domain.response

import com.sampleandroidapp.domain.entities.BaseResponse
import com.sampleandroidapp.domain.entities.EmployeeEntity

data class EmployeeResponse(
    var data: List<EmployeeEntity>
): BaseResponse()