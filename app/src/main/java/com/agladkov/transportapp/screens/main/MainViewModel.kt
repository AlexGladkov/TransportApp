package com.agladkov.transportapp.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.agladkov.domain.usecases.FetchBelgiumTransportUseCase

class MainViewModel : ViewModel() {

    lateinit var fetchBelgiumTransportUseCase: FetchBelgiumTransportUseCase

    fun printUseCase() {
        Log.e("TAG", fetchBelgiumTransportUseCase.toString())
    }
}