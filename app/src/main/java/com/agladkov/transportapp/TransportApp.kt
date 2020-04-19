package com.agladkov.transportapp

import android.app.Activity
import android.app.Application
import android.app.Presentation
import com.agladkov.domain.di.DaggerDomainComponent
import com.agladkov.domain.di.DomainComponent
import com.agladkov.presentation.di.DaggerPresentationComponent
import com.agladkov.presentation.di.PresentationComponent
import com.agladkov.transportapp.di.AppComponent
import com.agladkov.transportapp.di.DaggerAppComponent
import com.agladkov.utilities.di.DaggerUtilsComponent
import com.agladkov.utilities.di.UtilsComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TransportApp: Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .presentationComponent(providePresentationComponent())
            .utilsComponent(provideUtilsComponent())
            .build()

        appComponent.inject(this)
    }

    private fun providePresentationComponent(): PresentationComponent {
        return DaggerPresentationComponent
            .builder()
            .domainComponent(provideDomainComponent())
            .utilsComponent(provideUtilsComponent())
            .build()
    }

    private fun provideDomainComponent(): DomainComponent {
        return DaggerDomainComponent
            .builder()
            .build()
    }

    private fun provideUtilsComponent(): UtilsComponent {
        return DaggerUtilsComponent
            .builder()
            .application(this)
            .build()
    }
}