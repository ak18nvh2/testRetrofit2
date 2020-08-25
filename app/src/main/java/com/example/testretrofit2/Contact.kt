package com.example.testretrofit2

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Contact {
    @SerializedName("Email")
    @Expose
    var email: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("api_originated")
    @Expose
    var apiOriginated: Boolean? = null

    @SerializedName("Name")
    @Expose
    var name: String? = null

    @SerializedName("LastName")
    @Expose
    var lastName: String? = null

    @SerializedName("FirstName")
    @Expose
    var firstName: String? = null

    @SerializedName("contact_id")
    @Expose
    var contactId: String? = null


}