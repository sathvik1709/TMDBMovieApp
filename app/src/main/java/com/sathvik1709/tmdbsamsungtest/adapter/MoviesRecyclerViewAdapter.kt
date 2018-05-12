package com.sathvik1709.tmdbsamsungtest.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sathvik1709.tmdbsamsungtest.R
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.extensions.Util
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject

class MoviesRecyclerViewAdapter(val moviesList: List<Movie>, util : Util) :  RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    val util : Util = util
    val posterBaseURL = "https://image.tmdb.org/t/p/w500"
    val onViewClicked = PublishSubject.create<Movie>()

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val itemContainer = view.findViewById<ConstraintLayout>(R.id.item_container)
        val moviePosterImageView = view.findViewById<ImageView>(R.id.item_poster)
        val movieTitle = view.findViewById<TextView>(R.id.item_movie_name)
        val movieGenre = view.findViewById<TextView>(R.id.movie_item_genre)
        val moviePopularity = view.findViewById<TextView>(R.id.item_popularity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = moviesList[position].title
        holder.moviePopularity.text = moviesList[position].popularity.toInt().toString()

        holder.movieGenre.text = util.getPrettyDisplayList(moviesList[position].genre_names)

        // Load image from Picasso
        Picasso.get().load(posterBaseURL + moviesList[position].poster_path).into(holder.moviePosterImageView);

        // OnClickListener
        holder.itemContainer.setOnClickListener {
            onViewClicked.onNext(moviesList[position])
        }
    }

    override fun getItemCount(): Int = moviesList.size
}