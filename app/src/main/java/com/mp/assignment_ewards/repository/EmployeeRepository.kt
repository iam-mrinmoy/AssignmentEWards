package com.mp.assignment_ewards.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.isalepro.requestHandler.ApiResponse
import com.mp.assignment_ewards.requestHandler.ApiClient
import com.mp.assignment_ewards.requestHandler.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmployeeRepository {

    private var apiClient: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
    private val TAG = "RegisterRepositoryTAG"


    fun getAllEmployees(): MutableLiveData<ApiResponse> {
        val responseData: MutableLiveData<ApiResponse> = MutableLiveData<ApiResponse>()

        apiClient.getAllEmployees().enqueue(object : Callback<JsonElement> {
            override fun onResponse(
                call: Call<JsonElement>,
                response: Response<JsonElement>
            ) {
                if (response.isSuccessful) {
                    responseData.value = ApiResponse(response.body())
                } else {
                    responseData.value = ApiResponse(response.body(), false)
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                responseData.value = ApiResponse(t)
            }
        })
        return responseData
    }

}