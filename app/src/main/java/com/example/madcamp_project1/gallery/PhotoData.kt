package com.example.madcamp_project1.gallery

import java.io.Serializable

data class PhotoData(
    val photoUri: String,
    val photoDescription: String
) : Serializable
