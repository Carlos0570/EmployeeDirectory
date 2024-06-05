package com.example.employeedirectory.core.dataBase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.employeedirectory.core.data.Employee

@Dao
interface EmployeeDao {
    @Upsert
    suspend fun upsertEmployee(employees: List<Employee>)

    @Query("SELECT * FROM Employee")
    fun getEmployees(): List<Employee>
}