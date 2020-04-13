package com.agladkov.transportapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agladkov.transportapp.R
import com.agladkov.transportapp.TransportApp
import com.agladkov.transportapp.screens.splash.SplashFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var splashScreen: SplashFragment

    @Inject
    lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        TransportApp.appComponent.inject(activity = this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainActivity, mainFragment)
            .commitNow()
    }
}