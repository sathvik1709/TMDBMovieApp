package com.sathvik1709.tmdbsamsungtest.di

import android.content.Context
import com.sathvik1709.tmdbsamsungtest.MoviesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(moviesApp: MoviesApp) : Context = moviesApp.applicationContext

}