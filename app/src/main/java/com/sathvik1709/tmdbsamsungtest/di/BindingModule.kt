package com.sathvik1709.tmdbsamsungtest.di


import com.sathvik1709.tmdbsamsungtest.ui.activities.MovieDetailActivity
import com.sathvik1709.tmdbsamsungtest.ui.activities.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun bindMoviesActivity(): MoviesActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMovieDetailActivity(): MovieDetailActivity

}