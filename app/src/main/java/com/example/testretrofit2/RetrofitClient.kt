package com.example.testretrofit2

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://dummy.restapiexample.com/api/v1/"
    val gson = GsonBuilder().setLenient().create()
    val buider = OkHttpClient.Builder()
        .readTimeout(50000, TimeUnit.MILLISECONDS)
        .writeTimeout(50000, TimeUnit.MILLISECONDS)
        .connectTimeout(50000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true).build()
    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buider)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(Api::class.java)
    }
}