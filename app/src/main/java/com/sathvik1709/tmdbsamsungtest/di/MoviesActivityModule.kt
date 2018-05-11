package com.sathvik1709.tmdbsamsungtest.di

import com.sathvik1709.tmdbsamsungtest.ui.now_playing.NowPlayingModule
import com.sathvik1709.tmdbsamsungtest.ui.now_playing.NowPlayingView
import com.sathvik1709.tmdbsamsungtest.ui.upcoming.UpcomingMoviesModule
import com.sathvik1709.tmdbsamsungtest.ui.upcoming.UpcomingMoviesView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [NowPlayingModule::class])
    abstract fun bindNowPlayingView(): NowPlayingView

    @FragmentScope
    @ContributesAndroidInjector(modules = [UpcomingMoviesModule::class])
    abstract fun bindUpcomingMoviesView(): UpcomingMoviesView

}