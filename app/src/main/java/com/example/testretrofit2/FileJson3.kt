package com.example.testretrofit2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FileJson3 {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}