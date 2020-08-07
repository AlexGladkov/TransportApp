package com.agladkov.presentation.screens.cities

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.agladkov.presentation.R

interface CitiesRoutingLogic {
    fun routeToMain()
}

class CitiesRouter(val fragment: Fragment) : CitiesRoutingLogic {

    override fun routeToMain() {
        fragment
            .findNavController()
            .navigate(R.id.action_citiesFragment_to_navigation)
    }
}