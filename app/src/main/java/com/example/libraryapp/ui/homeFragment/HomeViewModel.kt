package com.example.libraryapp.ui.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libraryapp.api.ApiCallback
import com.example.libraryapp.api.AppRepository
import com.example.libraryapp.model.Product
import com.example.libraryapp.model.ProductAddedResponse

class HomeViewModel : ViewModel() {
    var apiResponse = MutableLiveData<ProductAddedResponse>()
    var apiError = MutableLiveData<String>()

    fun syncProduct(product: Product) {
        AppRepository.postAddProduct(
            product = product,
            object : ApiCallback<ProductAddedResponse> {
                override fun onException(error: Throwable) {
                    apiError.value = error.message
                }

                override fun onError(errorMsg: String) {
                    apiError.value = errorMsg
                }

                override fun onSuccess(t: ProductAddedResponse) {
                    apiResponse.value = t
                }

            })
    }
}