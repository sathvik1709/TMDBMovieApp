package com.sathvik1709.tmdbsamsungtest.ui.upcoming

import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.repo.CloudRepository
import com.sathvik1709.tmdbsamsungtest.repo.ServiceResponse
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import javax.inject.Inject

class UpcomingMoviesPresenter @Inject constructor(view: MoviesListContract.View) : MoviesListContract.Presenter {

    @Inject
    lateinit var repository: CloudRepository

    var view: MoviesListContract.View = view

    override fun getMoviesList(path: String) {
        view.showProgressIndicator()
        repository.getMoviesList1(object : ServiceResponse<List<Movie>> {
            override fun onSuccess(moviesList: List<Movie>) {
                view.showMoviesList(moviesList)
                view.hideProgressIndicator()
            }

            override fun onErrorMsg(errorMessage: String) {
                view.showErrorMessage()
                view.hideProgressIndicator()
            }
        }, path)
    }
}