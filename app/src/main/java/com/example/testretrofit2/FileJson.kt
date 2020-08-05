package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class FileJson {
    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<Employee?>? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getData(): List<Employee?>? {
        return data
    }

    fun setData(data: List<Employee?>?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}