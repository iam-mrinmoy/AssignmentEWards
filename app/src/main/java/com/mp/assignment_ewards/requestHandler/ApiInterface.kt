package com.mp.assignment_ewards.requestHandler

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("emp.json")
    fun getAllEmployees(): Call<JsonElement>
}
