package com.sathvik1709.tmdbsamsungtest.repo

import com.sathvik1709.tmdbsamsungtest.dto.Genre
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.dto.MoviesListResponse
import com.sathvik1709.tmdbsamsungtest.network.TMDBApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.BiFunction


class CloudRepository constructor(tmdbApiService: TMDBApiService) {

    val language : String
    val region : String
    val apiKey : String
    val service : TMDBApiService

    init {
        language = "en-US"
        region = "US"
        apiKey = "5dad61f29c7f330a81c8932caad6c3bd"
        service = tmdbApiService
    }

    fun getMoviesWithGenre(serviceResponse: ServiceResponse<List<Movie>>, path : String) {

        var moviesResponseSingle = service
                .getMoviesList(path, apiKey, language, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MoviesListResponse -> return@map response.results }

        var genreResponseSingle = service
                .getGenreIds(apiKey, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t ->
                    var genreMap : HashMap<Int, String> = HashMap()
                    t.genres.forEach { genre: Genre -> genreMap[genre.id] = genre.name }
                    genreMap
                }

        Single.zip(moviesResponseSingle, genreResponseSingle, object : BiFunction<List<Movie>, HashMap<Int, String>, List<Movie>> {
            override fun apply(moviesList: List<Movie>, genreMap: HashMap<Int, String>): List<Movie> {

                for (i in moviesList.indices) {
                    var movie = moviesList[i]
                    var genreNames: MutableList<String> = ArrayList()
                    movie.genre_ids.forEach { id: Int -> genreNames.add(genreMap[id]!!) }
                    movie.genre_names = genreNames
                }

//                moviesList.forEach { movie: Movie ->
//                    run {
//                        var genreNames: MutableList<String> = ArrayList()
//                        movie.genre_ids.forEach { id: Int -> genreNames.add(genreMap[id]!!) }
//                        movie.genre_names = genreNames
//                    }
//                }
                return moviesList
            }
        }).subscribe(
                { t -> serviceResponse.onSuccess(t) },
                { t -> serviceResponse.onErrorMsg(t.message!!) }
        )
    }
}
