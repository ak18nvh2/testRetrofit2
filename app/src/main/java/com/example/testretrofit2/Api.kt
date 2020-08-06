package com.example.testretrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface Api {
    @Headers(value = ["Accept: application/json",
        "Content-type:application/json"])
    @GET("employees/")
    fun getEmployees(): Call<FileJson>

}