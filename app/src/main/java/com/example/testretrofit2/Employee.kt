package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




 class Employee {
     @SerializedName("id")
     @Expose
     private var id: Int? = null

     @SerializedName("employee_name")
     @Expose
     private var employeeName: String? = null

     @SerializedName("employee_salary")
     @Expose
     private var employeeSalary: Int? = null

     @SerializedName("employee_age")
     @Expose
     private var employeeAge: Int? = null

     @SerializedName("profile_image")
     @Expose
     private var profileImage: String? = null

     fun getId(): Int? {
         return id
     }

     fun setId(id: Int?) {
         this.id = id
     }

     fun getEmployeeName(): String? {
         return employeeName
     }

     fun setEmployeeName(employeeName: String?) {
         this.employeeName = employeeName
     }

     fun getEmployeeSalary(): Int? {
         return employeeSalary
     }

     fun setEmployeeSalary(employeeSalary: Int?) {
         this.employeeSalary = employeeSalary
     }

     fun getEmployeeAge(): Int? {
         return employeeAge
     }

     fun setEmployeeAge(employeeAge: Int?) {
         this.employeeAge = employeeAge
     }

     fun getProfileImage(): String? {
         return profileImage
     }

     fun setProfileImage(profileImage: String?) {
         this.profileImage = profileImage
     }
 }