package com.example.libraryapp.session

import android.content.Context
import android.content.SharedPreferences
import com.example.libraryapp.LibraryApplication
import com.example.libraryapp.model.Product

class SavedPreferences private constructor() {

    private var sharedPreferences: SharedPreferences? = null

    private fun getEditor(): SharedPreferences.Editor? {
        return sharedPreferences?.edit()
    }

    fun saveProduct(value: Product) {
        val editor = getEditor()
        editor?.putString(NEW_PRODUCT_NAME, value.productName)
        editor?.putString(NEW_PRODUCT_DESC, value.productDesc)
        editor?.putInt(NEW_PRODUCT_PRICE, value.productPrice ?: 0)
        editor?.putInt(NEW_PRODUCT_QTY, value.productQty ?: 0)
        editor?.putLong(USER_NUMBER, value.mobileNumber)
        editor?.commit()
    }

    fun syncProduct(): Product {
        return Product(
            productName = sharedPreferences?.getString(NEW_PRODUCT_NAME, "").toString(),
            productDesc = sharedPreferences?.getString(NEW_PRODUCT_DESC, "").toString(),
            productQty = sharedPreferences?.getInt(NEW_PRODUCT_QTY, 0),
            productPrice = sharedPreferences?.getInt(NEW_PRODUCT_PRICE, 0),
            mobileNumber = sharedPreferences?.getLong(USER_NUMBER, 0)!!
        )
    }

    fun clearProduct(){
        val editor = getEditor()
        editor?.clear()
        editor?.apply()
    }
    companion object {

        private const val PREF_NAME = "APP_DATA"
        private const val NEW_PRODUCT_NAME = "new_product_name"
        private const val NEW_PRODUCT_DESC = "new_product_desc"
        private const val NEW_PRODUCT_PRICE = "new_product_price"
        private const val NEW_PRODUCT_QTY = "new_product_qty"
        private const val USER_NUMBER = "user_number"
        private var savedPreferences: SavedPreferences? = null

        fun init(context: Context) {
            savedPreferences = SavedPreferences()
            savedPreferences?.sharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }


        fun getInstance(): SavedPreferences? {
            if (savedPreferences == null) {
                init(LibraryApplication.libraryApplication?.applicationContext!!)
            }
            return savedPreferences
        }

    }

}