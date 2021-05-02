package com.example.libraryapp.api

import com.example.libraryapp.model.BookListResponse
import com.example.libraryapp.model.Product
import com.example.libraryapp.model.ProductAddedResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Details ApiService Interface for defining Apis
 */
interface ApiService {
    @GET("getAllAvailableBooks")
    fun getAllBooks(): Call<BookListResponse>

    @POST("addNewProduct")
    fun addNewBook(@Body product: Product): Call<ProductAddedResponse>
}