package com.mp.assignment_ewards.dto

import com.google.gson.annotations.SerializedName

data class EmployeeDto(
    @SerializedName("data")
    val empList: List<Emp>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Emp(
        @SerializedName("employee_age")
        val employeeAge: Int,
        @SerializedName("employee_name")
        val employeeName: String,
        @SerializedName("employee_salary")
        val employeeSalary: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("profile_image")
        val profileImage: String,

        var isSelected:Boolean=false
    )
}