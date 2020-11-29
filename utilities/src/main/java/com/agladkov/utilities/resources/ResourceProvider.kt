package com.agladkov.utilities.resources

import android.content.Context
import android.content.res.Resources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider(
    private val context: Context
) : IResourceProvider {

    override fun getResources(): Resources {
        return context.resources
    }

    override fun getString(stringId: Int): String {
        return context.resources.getString(stringId)
    }
}