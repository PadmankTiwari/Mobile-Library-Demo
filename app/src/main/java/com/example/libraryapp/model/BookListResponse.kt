package com.example.libraryapp.model

import com.google.gson.annotations.SerializedName

data class BookListResponse(
    @SerializedName("results")
    val result: List<BookInformation>,
)

data class BookInformation(
    @SerializedName("book_id")
    val bookId: Int,
    @SerializedName("book_name")
    val bookName: String,
    @SerializedName("book_desc")
    val bookDescription: String,
    @SerializedName("book_author")
    val bookAuthor: String,
    @SerializedName("book_price")
    val bookPrice: String,
    @SerializedName("book_img_url")
    val bookImageUrl: String
)