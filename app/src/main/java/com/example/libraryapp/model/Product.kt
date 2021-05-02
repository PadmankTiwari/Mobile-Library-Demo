package com.example.libraryapp.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("product_desc")
    var productDesc: String,
    @SerializedName("product_quantity")
    var productQty: Int?,
    @SerializedName("product_price")
    var productPrice: Int?,
    @SerializedName("user_mobile_no")
    var mobileNumber: Long
)