package com.agladkov.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun <T> Fragment.getNavigationResult(key: String): T? =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.value

fun <T> Fragment.setNavigationResult(key: String, value: T) =
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)