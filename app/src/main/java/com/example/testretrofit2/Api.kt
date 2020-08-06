package com.example.testretrofit2

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers(value = ["Accept: application/json",
        "Content-type:application/json"])
    @GET("employees/")
    fun getEmployees(): Call<FileJson>


    @Headers(value = ["Accept: application/json",
        "Content-type:application/json"])
    @FormUrlEncoded
    @POST("create/")
    fun insertEmployee(@Field("id") id : Int?,
                       @Field("employee_name") employeeName : String?,
                       @Field("employee_salary") employeeSalary : Int?,
                       @Field("employee_age") employeeAge : Int?,
                       @Field("profile_image") profileImage : String? ) : Call<Employee>
}

//fun insertEmployee(@Field("id") id : String,
//                   @Field("employee_name") employeeName : String,
//                   @Field("employee_salary") employeeSalary : String,
//                   @Field("employee_age") employeeAge : String,
//                   @Field("profile_image") profileImage : String ) : Call<String>