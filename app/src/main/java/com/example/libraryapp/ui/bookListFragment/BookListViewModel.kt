package com.example.libraryapp.ui.bookListFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libraryapp.api.ApiCallback
import com.example.libraryapp.api.AppRepository
import com.example.libraryapp.model.BookListResponse

class BookListViewModel : ViewModel() {
    var apiResponse = MutableLiveData<BookListResponse>()
    var apiError = MutableLiveData<String>()

    fun callGetAllBooks() {
        AppRepository.getAllBooks(object : ApiCallback<BookListResponse> {
            override fun onException(error: Throwable) {
                apiError.value = error.message
            }

            override fun onError(errorMsg: String) {
                apiError.value = errorMsg
            }

            override fun onSuccess(t: BookListResponse) {
                apiResponse.value = t
            }

        })
    }
}