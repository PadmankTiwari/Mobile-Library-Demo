package com.example.libraryapp.ui.addProductFragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentAddProductBinding
import com.example.libraryapp.model.Product
import com.example.libraryapp.session.SavedPreferences
import com.example.libraryapp.utils.Constants

class AddProductFragment : Fragment() {

    private lateinit var mBinding: FragmentAddProductBinding

    private lateinit var addProductViewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        addProductViewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        mBinding.addProductViewModel = addProductViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        mBinding.clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnSave -> {
                    if (isValidateData()) {
                        SavedPreferences.getInstance()?.saveProduct(
                            Product(
                                productName = addProductViewModel.productName.value!!,
                                productPrice = Integer.parseInt(addProductViewModel.productPrice.value!!),
                                productQty = Integer.parseInt(addProductViewModel.productQty.value!!),
                                productDesc = addProductViewModel.productDesc.value!!,
                                mobileNumber = Constants.USER_MOBILE
                            )
                        )
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.successfully_added_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    } else
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.incomplete_details_msg),
                            Toast.LENGTH_SHORT
                        ).show()

                }
                R.id.btnBack -> findNavController().popBackStack()
            }
        }
    }

    private fun isValidateData(): Boolean {
        clearError()
        var flag = true
        when {
            TextUtils.isEmpty(addProductViewModel.productName.value) -> {
                mBinding.tilProductName.error = getString(R.string.name_empty_error)
                flag = false
            }
            TextUtils.isEmpty(addProductViewModel.productDesc.value) -> {
                mBinding.tilProductDesc.error = getString(R.string.desc_empty_error)
                flag = false
            }
            TextUtils.isEmpty(addProductViewModel.productQty.value) -> {
                mBinding.tilProductQty.error = getString(R.string.qty_empty_error)
                flag = false
            }
            TextUtils.isEmpty(addProductViewModel.productPrice.value) -> {
                mBinding.tilProductPrice.error = getString(R.string.price_empty_error)
                flag = false
            }
        }
        return flag
    }

    private fun clearError() {
        mBinding.tilProductName.isErrorEnabled = false
        mBinding.tilProductDesc.isErrorEnabled = false
        mBinding.tilProductQty.isErrorEnabled = false
        mBinding.tilProductPrice.isErrorEnabled = false
    }
}