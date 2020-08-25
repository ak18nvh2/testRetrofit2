package com.example.testretrofit2

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers(
        value = ["Accept: application/json",
            "Content-type:application/json",
            "autopilotapikey:e2cff516881142c8b20fe3febf24d515"
        ]
    )
    @GET("contacts/bookmark")
    fun getContacts(): Call<FJson>


//    @Headers(
//        value = ["Accept: application/json",
//            "Content-type:application/json",
//            "autopilotapikey:e2cff516881142c8b20fe3febf24d515"]
//    )
//    @POST("create")
//    fun insertEmployee(@Body employee: Employee): Call<FJson>
//
//
//    @Headers(
//        value = ["Accept: application/json",
//            "Content-type:application/json"]
//    )
////    @DELETE("delete/{id}")
////    fun deleteEmployee(@Path("id") id: Int): Call<FileJson3>
}
