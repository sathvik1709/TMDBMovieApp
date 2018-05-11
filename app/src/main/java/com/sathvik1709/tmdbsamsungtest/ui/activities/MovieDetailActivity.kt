package com.sathvik1709.tmdbsamsungtest.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sathvik1709.tmdbsamsungtest.R
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

//        val movie = intent.getParcelableExtra<Movie>("SELECTED_MOVIE")
//        setUpView(movie)
    }

    private fun setUpView(movie: Movie){
        movieTitle.text = movie.title
        movieDescription.text = movie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(moviePoster)
    }
}
