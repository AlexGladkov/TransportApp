package com.agladkov.domain.di

import dagger.Component

@Component
@DomainScope
interface DomainComponent {

    @Component.Builder
    interface Builder {
        fun build(): DomainComponent
    }
}