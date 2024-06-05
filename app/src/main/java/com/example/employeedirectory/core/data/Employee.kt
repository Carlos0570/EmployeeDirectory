package com.example.employeedirectory.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class DirectoryResponse(
    @SerializedName("employees")
    var employees: ArrayList<Employee> = arrayListOf()
)

@Entity
data class Employee(
    @PrimaryKey
    @SerializedName("uuid")
    var uuid: String,

    @SerializedName("full_name")
    var fullName: String,

    @SerializedName("phone_number")
    var phoneNumber: String? = null,

    @SerializedName("email_address")
    var emailAddress: String,

    @SerializedName("biography")
    var biography: String? = null,

    @SerializedName("photo_url_small")
    var photoUrlSmall: String? = null,

    @SerializedName("photo_url_large")
    var photoUrlLarge: String? = null,

    @SerializedName("team")
    var team: String,

    @SerializedName("employee_type")
    var employeeType: EmployeeType
)
