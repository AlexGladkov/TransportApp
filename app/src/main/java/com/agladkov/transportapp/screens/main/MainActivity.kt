package com.agladkov.transportapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agladkov.presentation.screens.countries.CountriesFragment
import com.agladkov.transportapp.R
import dagger.android.AndroidInjection

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}