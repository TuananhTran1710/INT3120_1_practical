package com.example.amphibians.model

import com.google.gson.annotations.SerializedName

data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerializedName("img_src") val imgSrc: String
)
