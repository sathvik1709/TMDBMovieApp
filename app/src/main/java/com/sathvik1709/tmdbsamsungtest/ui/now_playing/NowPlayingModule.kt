package com.sathvik1709.tmdbsamsungtest.ui.now_playing

import com.sathvik1709.tmdbsamsungtest.di.FragmentScope
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import dagger.Binds
import dagger.Module

@Module
abstract class NowPlayingModule {

    @Binds
    @FragmentScope
    abstract fun provideNowPlayingView(nowPlayingView: NowPlayingView) : MoviesListContract.View

    @Binds
    @FragmentScope
    abstract fun provideNowPlayingPresenter(nowPlayingPresenter: NowPlayingPresenter) : MoviesListContract.Presenter

}