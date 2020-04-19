package com.agladkov.transportapp.di

import android.app.Application
import com.agladkov.domain.di.DomainComponent
import com.agladkov.presentation.di.PresentationComponent
import com.agladkov.presentation.di.ScreenBindingModule
import com.agladkov.transportapp.TransportApp
import com.agladkov.transportapp.di.modules.ActivityBindingModule
import com.agladkov.transportapp.screens.main.MainActivity
import com.agladkov.utilities.di.UtilsComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import java.security.DomainCombiner

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ScreenBindingModule::class
    ],
    dependencies = [PresentationComponent::class, UtilsComponent::class]
)
@AppScope
interface AppComponent: AndroidInjector<TransportApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun presentationComponent(presentationComponent: PresentationComponent): AppComponent.Builder
        fun utilsComponent(utilsComponent: UtilsComponent): AppComponent.Builder

        fun build(): AppComponent
    }
}