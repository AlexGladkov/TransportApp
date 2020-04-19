package com.agladkov.domain.di

import com.agladkov.domain.di.modules.UseCasesModule
import dagger.Component

@Component(
    modules = [UseCasesModule::class]
)
@DomainScope
interface DomainComponent {

    @Component.Builder
    interface Builder {
        fun build(): DomainComponent
    }
}