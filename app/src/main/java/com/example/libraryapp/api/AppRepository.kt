package com.example.libraryapp.api

import com.example.libraryapp.model.BookListResponse
import com.example.libraryapp.model.Product
import com.example.libraryapp.model.ProductAddedResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object AppRepository {

    private fun parseError(response: Response<Any>, callBack: ApiCallback<Any>) {
        try {
            val jobError = JSONObject(response.errorBody()!!.string())
            val errorBody = jobError.getString(ApiConstants.MESSAGE)
            callBack.onError(errorBody)

        } catch (e: JSONException) {
            callBack.onException(Throwable(ApiConstants.ERROR))
            e.printStackTrace()
        } catch (e: IOException) {
            callBack.onException(Throwable(ApiConstants.ERROR))
            e.printStackTrace()
        }
    }

    fun getAllBooks(callBack: ApiCallback<BookListResponse>) {
        val retrofit = ApiClient.retrofit
        val callGet = retrofit?.create(ApiService::class.java)?.getAllBooks()
        callGet?.enqueue(object : Callback<BookListResponse> {
            override fun onResponse(
                call: Call<BookListResponse>,
                response: Response<BookListResponse>
            ) {
                if (response.isSuccessful.not()) {
                    parseError(response as Response<Any>, callBack as ApiCallback<Any>)
                } else {
                    response.body()?.let { callBack.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                callBack.onError(ApiConstants.ERROR)
            }

        })
    }

    fun postAddProduct(
        product: Product,
        callBack: ApiCallback<ProductAddedResponse>
    ) {
        val retrofit = ApiClient.retrofit
        val callPost = retrofit?.create(ApiService::class.java)
            ?.addNewBook(product = product)
        callPost?.enqueue(object : Callback<ProductAddedResponse> {
            override fun onResponse(
                call: Call<ProductAddedResponse>,
                response: Response<ProductAddedResponse>
            ) {
                if (!response.isSuccessful) {
                    parseError(response as Response<Any>, callBack as ApiCallback<Any>)
                } else {
                    response.body()?.let { callBack.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ProductAddedResponse>, t: Throwable) {
                callBack.onError(ApiConstants.ERROR)
            }

        })
    }
}