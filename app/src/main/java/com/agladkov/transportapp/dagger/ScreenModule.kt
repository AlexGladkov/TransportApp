package com.agladkov.transportapp.dagger

import com.agladkov.transportapp.screens.main.MainFragment
import com.agladkov.transportapp.screens.splash.SplashFragment
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @Provides
    fun provideSplashScreen(): SplashFragment {
        return SplashFragment()
    }

    @Provides
    fun provideMainScreen(): MainFragment {
        return MainFragment()
    }
}