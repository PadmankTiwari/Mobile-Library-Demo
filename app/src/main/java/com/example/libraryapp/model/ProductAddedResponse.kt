package com.example.libraryapp.model

import com.google.gson.annotations.SerializedName

data class ProductAddedResponse(
    @SerializedName("results")
    val results: Results
)

data class Results(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Int
)