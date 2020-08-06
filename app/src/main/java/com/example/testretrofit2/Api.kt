package com.example.testretrofit2

import com.example.FileJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @GET("employees/")
    fun getEmployees(): Call<FileJson>
}