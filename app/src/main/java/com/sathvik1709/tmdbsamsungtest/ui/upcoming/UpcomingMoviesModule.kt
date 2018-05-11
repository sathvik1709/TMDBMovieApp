package com.sathvik1709.tmdbsamsungtest.ui.upcoming

import com.sathvik1709.tmdbsamsungtest.di.FragmentScope
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import com.sathvik1709.tmdbsamsungtest.ui.now_playing.NowPlayingPresenter
import com.sathvik1709.tmdbsamsungtest.ui.now_playing.NowPlayingView
import dagger.Binds
import dagger.Module

@Module
abstract class UpcomingMoviesModule {

    @Binds
    @FragmentScope
    abstract fun provideUpcomingMoviesView(upcomingMoviesView: UpcomingMoviesView) : MoviesListContract.View

    @Binds
    @FragmentScope
    abstract fun provideUpcomingMoviesPresenter(upcomingMoviesPresenter: UpcomingMoviesPresenter) : MoviesListContract.Presenter

}