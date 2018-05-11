package com.sathvik1709.tmdbsamsungtest.network

import com.sathvik1709.tmdbsamsungtest.dto.GenreResponse
import com.sathvik1709.tmdbsamsungtest.dto.MoviesListResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


interface TMDBApiService {

    @GET("3/movie/{moviesListPath}")
    fun getMoviesList(
            @Path("moviesListPath") moviesListPath : String,
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("region") region: String) : Single<MoviesListResponse>

    @GET("3/genre/movie/list")
    fun getGenreIds(
            @Query("api_key") api_key: String,
            @Query("language") language: String) : Single<GenreResponse>

}