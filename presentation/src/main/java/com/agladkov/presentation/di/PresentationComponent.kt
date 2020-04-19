package com.agladkov.presentation.di

import android.app.Application
import com.agladkov.domain.di.DomainComponent
import com.agladkov.utilities.di.UtilsComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ScreenBindingModule::class],
    dependencies = [DomainComponent::class, UtilsComponent::class]
)
@PresentationScope
interface PresentationComponent {

    @Component.Builder
    interface Builder {

        fun domainComponent(domainComponent: DomainComponent): PresentationComponent.Builder
        fun utilsComponent(utilsComponent: UtilsComponent): PresentationComponent.Builder

        fun build(): PresentationComponent
    }
}