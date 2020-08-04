package com.agladkov.presentation.screens.countries.models

sealed class CountriesEvent {
    object ScreenShown : CountriesEvent()
    object MoreClick : CountriesEvent()
}