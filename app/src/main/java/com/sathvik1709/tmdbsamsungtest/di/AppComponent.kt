package com.sathvik1709.tmdbsamsungtest.di

import com.sathvik1709.tmdbsamsungtest.MoviesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    BindingModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(moviesApp: MoviesApp): Builder
    }

    fun inject(moviesApp: MoviesApp)
}