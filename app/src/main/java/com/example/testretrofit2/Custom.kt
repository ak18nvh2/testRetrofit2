package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Custom {
    @SerializedName("string--image")
    @Expose
    var stringImage: String? = null

    @SerializedName("string--age")
    @Expose
    var stringAge: String? = null
}