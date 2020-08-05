package com.example.testretrofit2

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("employees")
    fun getEmployees(): Call<ArrayList<FileJson>>

}