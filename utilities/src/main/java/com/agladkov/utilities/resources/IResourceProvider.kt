package com.agladkov.utilities.resources

import android.content.res.Resources

interface IResourceProvider {
    fun getResources(): Resources
    fun getString(stringId: Int): String
}