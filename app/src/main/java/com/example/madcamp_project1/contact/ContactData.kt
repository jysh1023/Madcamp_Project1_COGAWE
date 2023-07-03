package com.example.madcamp_project1.contact

import com.google.gson.annotations.SerializedName

data class ContactData (
    @SerializedName("name") var name: String,
    @SerializedName("contact") var contact: String,
    @SerializedName("school") var school: String,
    @SerializedName("interest") var interest: String



)



