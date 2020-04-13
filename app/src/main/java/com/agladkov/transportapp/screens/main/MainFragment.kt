package com.agladkov.transportapp.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.agladkov.transportapp.R
import com.agladkov.transportapp.TransportApp
import javax.inject.Inject

class MainFragment: Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TransportApp.appComponent.inject(fragment = this)

        mainViewModel.printUseCase()
    }
}