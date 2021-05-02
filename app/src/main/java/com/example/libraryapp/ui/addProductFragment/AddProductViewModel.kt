package com.example.libraryapp.ui.addProductFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddProductViewModel : ViewModel() {
    val productName = MutableLiveData("")
    val productDesc = MutableLiveData("")
    val productQty = MutableLiveData("")
    val productPrice = MutableLiveData("")


}