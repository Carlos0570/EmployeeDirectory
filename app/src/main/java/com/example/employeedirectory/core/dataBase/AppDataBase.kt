package com.example.employeedirectory.core.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.employeedirectory.core.data.Employee

@Database(version = 1, entities = [Employee::class])
abstract class AppDataBase : RoomDatabase(){
    abstract fun getEmployeeDao(): EmployeeDao
}