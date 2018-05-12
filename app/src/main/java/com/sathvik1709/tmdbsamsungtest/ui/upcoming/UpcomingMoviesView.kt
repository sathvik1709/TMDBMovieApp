package com.sathvik1709.tmdbsamsungtest.ui.upcoming

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import com.sathvik1709.tmdbsamsungtest.ui.base.BaseMoviesListFragment
import javax.inject.Inject

class UpcomingMoviesView : BaseMoviesListFragment(), MoviesListContract.View {

    @Inject
    lateinit var upcomingMoviesPresenter : MoviesListContract.Presenter

    override fun getRequestPath(): String = "upcoming"
    override fun getPresenter(): MoviesListContract.Presenter = upcomingMoviesPresenter

    override fun showMoviesList(list: List<Movie>) {
        setUpRecyclerView(list)
    }

    override fun showErrorMessage() {

    }

    override fun showProgressIndicator() {

    }

    override fun hideProgressIndicator() {

    }

}