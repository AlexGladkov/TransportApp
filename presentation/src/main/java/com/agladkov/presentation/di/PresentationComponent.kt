package com.agladkov.presentation.di

import com.agladkov.domain.di.DomainComponent
import com.agladkov.utilities.di.UtilsComponent
import dagger.Component

@Component(
    dependencies = [DomainComponent::class, UtilsComponent::class]
)
@PresentationScope
interface PresentationComponent {

    @Component.Builder
    interface Builder {

        fun domainComponent(domainComponent: DomainComponent): Builder
        fun utilsComponent(utilsComponent: UtilsComponent): Builder

        fun build(): PresentationComponent
    }
}