package com.example.madcamp_project1.gallery

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PhotoData(
    @SerializedName("uri") val photoUri: String,
    @SerializedName("description") val photoDescription: String
) : Serializable
