package com.sathvik1709.tmdbsamsungtest.repo

import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.dto.MoviesListResponse
import com.sathvik1709.tmdbsamsungtest.network.TMDBApiService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.Disposable



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

}