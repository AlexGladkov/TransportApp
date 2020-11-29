package com.agladkov.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface PresentationComponent {

    @Component.Builder
    interface Builder {
        fun build(): PresentationComponent
    }
}