package com.example.madcamp_project1.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContactData (
    @SerializedName("name") val name: String,
    @SerializedName("contact") val contact: String
)
