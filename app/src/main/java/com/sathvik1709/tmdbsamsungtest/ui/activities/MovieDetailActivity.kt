package com.sathvik1709.tmdbsamsungtest.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.sathvik1709.tmdbsamsungtest.R
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.extensions.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.ArrayList

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar!!.title = "Details"

        val movieName = intent.getStringExtra("MOVIE_TITLE")
        val movieReleaseDate = intent.getStringExtra("RELEASE_DATE")
        val movieOverview = intent.getStringExtra("OVERVIEW")
        val movieVotes = intent.getIntExtra("VOTES", 0)
        val movieGenres = intent.getStringArrayListExtra("GENRES")
        val moviePosterPath = intent.getStringExtra("POSTER")

        movieTitle.text = movieName
        movieDescription.text = movieOverview
        movie_detail_release_date.text = movieReleaseDate
        movie_detail_votes.text = movieVotes.toString()
        movie_detail_genre.text = Util().getPrettyDisplayList(movieGenres)

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + moviePosterPath).into(moviePoster)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
