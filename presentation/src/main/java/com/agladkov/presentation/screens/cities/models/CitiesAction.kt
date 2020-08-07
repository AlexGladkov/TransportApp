package com.agladkov.presentation.screens.cities.models

sealed class CitiesAction {
    data class ShowSnackbar(val message: String) : CitiesAction()
}