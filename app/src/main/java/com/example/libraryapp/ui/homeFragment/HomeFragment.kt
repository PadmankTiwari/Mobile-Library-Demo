package com.example.libraryapp.ui.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentHomeBinding
import com.example.libraryapp.session.SavedPreferences
import com.example.libraryapp.utils.Utils

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mBinding: FragmentHomeBinding
    private var apiCalled = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mBinding.homeViewModel = homeViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        observeResponse()
    }

    private fun onClick() {
        mBinding.clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnAdd -> {
                    findNavController().navigate(R.id.action_homeFragment_to_addProductFragment)
                }
                R.id.btnSync -> {
                    if (Utils.isConnected())
                        SavedPreferences.getInstance()?.syncProduct()?.let { product ->
                            if (product.productName.isEmpty().not()) {
                                homeViewModel.syncProduct(product)
                                SavedPreferences.getInstance()?.clearProduct()
                                apiCalled = false

                            } else
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.updated_data_msg),
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                    else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.no_internet_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun observeResponse() {
        if (apiCalled) {
            homeViewModel.apiResponse.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it.results.message, Toast.LENGTH_SHORT).show()
            })
        }
    }

}