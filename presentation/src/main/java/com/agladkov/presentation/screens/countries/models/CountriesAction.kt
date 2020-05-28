package com.agladkov.presentation.screens.countries.models

sealed class CountriesAction {
    data class ShowSnackbar(val message: String) : CountriesAction()
}