package com.sathvik1709.tmdbsamsungtest.ui.now_playing


import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import com.sathvik1709.tmdbsamsungtest.ui.base.BaseMoviesListFragment
import javax.inject.Inject


class NowPlayingView : BaseMoviesListFragment(), MoviesListContract.View {

    @Inject
    lateinit var nowPlayingPresenter: MoviesListContract.Presenter

    override fun getRequestPath(): String = "now_playing"
    override fun getPresenter(): MoviesListContract.Presenter = nowPlayingPresenter

    override fun showMoviesList(list: List<Movie>) {
        setUpRecyclerView(list)
    }

    override fun showErrorMessage(errorMessage: String) {
        showErrorDialog(errorMessage)
    }

    override fun showProgressIndicator() {
        showProgressBar()
    }

    override fun hideProgressIndicator() {
        hideProgressBar()
    }
}