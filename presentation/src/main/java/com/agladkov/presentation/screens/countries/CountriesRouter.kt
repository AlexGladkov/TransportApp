package com.agladkov.presentation.screens.countries

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.agladkov.presentation.R
import com.agladkov.presentation.screens.countries.adapter.CountryCellModel

interface CountriesRoutingLogic {
    /// Country was selected by user
    fun routeToCities(country: CountryCellModel)
    /// User can't select this country
    fun routeToWrongCountryAlert()
}

class CountriesRouter(private val fragment: Fragment) :
    CountriesRoutingLogic {

    override fun routeToCities(country: CountryCellModel) {
        val bundle = bundleOf("CountryKey" to country)

        fragment
            .findNavController()
            .navigate(R.id.action_countriesFragment_to_citiesFragment, bundle)
    }

    override fun routeToWrongCountryAlert() {
        fragment
            .findNavController()
            .navigate(R.id.action_countriesFragment_to_wrongCountryDialog)
    }
}