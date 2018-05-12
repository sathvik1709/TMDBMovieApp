package com.sathvik1709.tmdbsamsungtest.ui

import com.sathvik1709.tmdbsamsungtest.dto.Movie

interface MoviesListContract {

    interface View {
        fun showMoviesList(list : List<Movie>)
        fun showErrorMessage(errorMessage : String)
        fun showProgressIndicator()
        fun hideProgressIndicator()
    }

    interface Presenter {
        fun getMoviesList(path: String)
    }

}