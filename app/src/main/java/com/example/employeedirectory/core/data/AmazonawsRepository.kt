package com.example.employeedirectory.core.data

import com.example.employeedirectory.core.dataBase.EmployeeDao
import com.example.employeedirectory.core.network.AmazonawsAPI
import com.example.employeedirectory.core.network.Result
import com.example.employeedirectory.core.network.safeAPICall
import javax.inject.Inject

class AmazonawsRepository @Inject constructor(
    private val amazonawsApi: AmazonawsAPI,
    private val dao: EmployeeDao
) {
    suspend fun getEmployees(): Result<List<Employee>> = safeAPICall {
        val localDataEmployees = dao.getEmployees()
        if (localDataEmployees.isNotEmpty())
            return@safeAPICall localDataEmployees
        else{
            val response = amazonawsApi.getEmployeeDirectory().employees
            dao.upsertEmployee(response)
            response
        }
    }
}