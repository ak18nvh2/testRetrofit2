package com.example.testretrofit2

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers(
        value = ["Accept: application/json",
            "Content-type:application/json"]
    )
    @GET("employees/")
    fun getEmployees(): Call<FileJson>


    @Headers(
        value = ["Accept: application/json",
            "Content-type:application/json"]
    )
    @POST("create")
    fun insertEmployee(@Body employee: Employee): Call<Employee>

    @FormUrlEncoded
    @PUT("update/{id}")
    fun updateEmployee(
        @Path("id") id: Int,
        @Field("name") employeeName: String,
        @Field("salary") employeeSalary: Int,
        @Field("age") employeeAge: Int
    ) : Call<Employee>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Unit>


}

//fun insertEmployee(@Field("id") id : String,
//                   @Field("employee_name") employeeName : String,
//                   @Field("employee_salary") employeeSalary : String,
//                   @Field("employee_age") employeeAge : String,
//                   @Field("profile_image") profileImage : String ) : Call<String>