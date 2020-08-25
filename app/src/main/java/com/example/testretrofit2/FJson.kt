package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class FJson {
    @SerializedName("contacts")
    @Expose
    var contacts: List<Contact>? = null

    @SerializedName("total_contacts")
    @Expose
    var totalContacts: Int? = null
}