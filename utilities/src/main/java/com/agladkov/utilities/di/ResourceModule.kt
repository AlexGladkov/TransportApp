package com.agladkov.utilities.di

import com.agladkov.utilities.resources.IResourceProvider
import com.agladkov.utilities.resources.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ResourceModule {

    @Binds
    abstract fun bindResourceProvider(resourceProvider: ResourceProvider): IResourceProvider
}
