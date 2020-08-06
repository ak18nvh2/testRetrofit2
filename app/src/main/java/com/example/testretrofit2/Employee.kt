package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




 class Employee {
     @SerializedName("id")
     @Expose
      var id: Int? = null

     @SerializedName("employee_name")
     @Expose
      var employeeName: String? = null

     @SerializedName("employee_salary")
     @Expose
      var employeeSalary: Int? = null

     @SerializedName("employee_age")
     @Expose
      var employeeAge: Int? = null

     @SerializedName("profile_image")
     @Expose
      var profileImage: String? = null


 }