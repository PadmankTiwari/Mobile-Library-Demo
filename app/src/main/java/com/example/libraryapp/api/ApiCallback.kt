package com.example.libraryapp.api

interface ApiCallback<T> {
    fun onException(error: Throwable)
    fun onError(errorMsg: String)
    fun onErrorCode(errorCode: Int) {}
    fun onSuccess(t: T)
}