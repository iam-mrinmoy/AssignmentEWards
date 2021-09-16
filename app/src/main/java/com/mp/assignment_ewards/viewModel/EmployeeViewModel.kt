package com.mp.assignment_ewards.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isalepro.requestHandler.ApiResponse
import com.mp.assignment_ewards.repository.EmployeeRepository

class EmployeeViewModel : ViewModel() {

    private val repository = EmployeeRepository

    fun getAllEmployees(
    ): LiveData<ApiResponse>? {
        return repository.getAllEmployees()
    }
}