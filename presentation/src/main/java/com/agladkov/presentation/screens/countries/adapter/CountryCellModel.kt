package com.agladkov.presentation.screens.countries.adapter

import androidx.annotation.DrawableRes
import com.agladkov.domain.usecases.countries.models.CountryModel
import com.agladkov.presentation.R

data class CountryCellModel(
    val id: Int,
    val title: String,
    @DrawableRes val icon: Int
)

// Mappers
fun CountryModel.mapToCountryCellModel(): CountryCellModel {
    return CountryCellModel(id = 0, title = this.title, icon = R.drawable.ic_baseline_where_to_vote_24)
}