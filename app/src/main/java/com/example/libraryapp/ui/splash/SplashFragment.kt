package com.example.libraryapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var splashViewModel: SplashViewModel
    private lateinit var mBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        mBinding.splashViewModel = splashViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToMain()
    }

    private fun navigateToMain() {
        lifecycleScope.launchWhenResumed {
            findNavController().navigate(R.id.action_splashFragment_to_landingFragment)
        }
    }
}