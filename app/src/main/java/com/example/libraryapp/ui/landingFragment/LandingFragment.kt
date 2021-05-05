package com.example.libraryapp.ui.landingFragment

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
import com.example.libraryapp.databinding.FragmentLandingBinding
import com.example.libraryapp.utils.Utils

class LandingFragment : Fragment() {

    private lateinit var landingViewModel: LandingViewModel
    private lateinit var mBinding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        landingViewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
        mBinding.landingViewModel = landingViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        mBinding.clickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnAddBook -> {
                    findNavController().navigate(R.id.action_landingFragment_to_homeFragment)
                }
                R.id.btnShowBooks -> {
                    if (Utils.isConnected())
                        findNavController().navigate(R.id.action_landingFragment_to_bookListFragment)
                    else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.no_internet_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}