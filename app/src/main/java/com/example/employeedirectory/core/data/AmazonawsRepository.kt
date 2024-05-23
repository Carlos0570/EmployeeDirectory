package com.example.employeedirectory.core.data

import com.example.employeedirectory.core.network.AmazonawsAPI
import com.example.employeedirectory.core.network.Result
import com.example.employeedirectory.core.network.safeAPICall
import javax.inject.Inject

class AmazonawsRepository @Inject constructor(private val amazonawsApi: AmazonawsAPI) {

    suspend fun getEmployees(): Result<List<Employee>> = safeAPICall{
        amazonawsApi.getEmployeeDirectory().employees
    }
}