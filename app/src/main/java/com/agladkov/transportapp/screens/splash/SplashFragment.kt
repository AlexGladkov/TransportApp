package com.agladkov.transportapp.screens.splash

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.agladkov.transportapp.R
import com.agladkov.transportapp.TransportApp
import com.agladkov.transportapp.screens.main.MainViewModel

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private lateinit var viewModel: SplashViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.application?.let { application ->
            viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(SplashViewModel::class.java)
        }
    }

}