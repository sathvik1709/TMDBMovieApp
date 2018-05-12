package com.sathvik1709.tmdbsamsungtest.repo

import com.sathvik1709.tmdbsamsungtest.dto.Genre
import com.sathvik1709.tmdbsamsungtest.dto.GenreResponse
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.dto.MoviesListResponse
import com.sathvik1709.tmdbsamsungtest.network.TMDBApiService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function


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

    fun getMoviesList(serviceResponse: ServiceResponse<List<Movie>>, path : String) {
        service.getMoviesList(path, apiKey, language, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MoviesListResponse -> return@map response.results }
                .subscribe(object : SingleObserver<List<Movie>>{
                    override fun onSuccess(moviesList: List<Movie>) {
                        serviceResponse.onSuccess(moviesList)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        serviceResponse.onErrorMsg(e.localizedMessage)
                    }
                })

    }

    fun getMoviesList1(serviceResponse: ServiceResponse<List<Movie>>, path : String) {

        var moviesResponseSingle = service
                .getMoviesList(path, apiKey, language, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MoviesListResponse -> return@map response.results }
                //.onErrorReturn { t -> null }

        var genreResponseSingle = service
                .getGenreIds(apiKey, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t ->
                    var genreMap : HashMap<Int, String> = HashMap()
                    t.genres.forEach { genre: Genre -> genreMap[genre.id] = genre.name }
                    genreMap
                }
                //.onErrorReturn { t -> null }

        Single.zip(moviesResponseSingle, genreResponseSingle, object : BiFunction<List<Movie>, HashMap<Int, String>, List<Movie>> {
            override fun apply(moviesList: List<Movie>, genreMap: HashMap<Int, String>): List<Movie> {

//                if(moviesList == null || genreMap == null ){
//                    serviceResponse.onErrorMsg("Something went wrong, please try again")
//                }

                moviesList.forEach { movie: Movie ->
                    run {
                        var genreNames: MutableList<String> = ArrayList()
                        movie.genre_ids.forEach { id: Int -> genreNames.add(genreMap[id]!!) }
                        movie.genre_names = genreNames
                    }
                }
                return moviesList
            }
        }).subscribe(
                { t -> serviceResponse.onSuccess(t) },
                { t -> serviceResponse.onErrorMsg(t.localizedMessage) }
        )
    }
}
