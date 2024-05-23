package com.example.employeedirectory.core.network

import com.example.employeedirectory.core.data.DirectoryResponse
import retrofit2.http.GET

interface AmazonawsAPI {
    @GET("sq-mobile-interview/employees.json")
    // @GET("sq-mobile-interview/employees_malformed.json")
    //@GET("sq-mobile-interview/employees_empty.json")
    suspend fun getEmployeeDirectory(): DirectoryResponse
}