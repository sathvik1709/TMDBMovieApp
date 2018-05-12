package com.sathvik1709.tmdbsamsungtest.dto

import android.os.Parcel
import android.os.Parcelable


data class MoviesListResponse(
        val results: List<Movie>,
        val page: Int,
        val total_results: Int,
        val dates: Dates,
        val total_pages: Int
)

data class Dates(
        val maximum: String,
        val minimum: String
)

// Has Parcelable implementation so the class looks BIG :)
data class Movie(
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        var genre_names: List<String>,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String
)