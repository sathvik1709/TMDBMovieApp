package com.sathvik1709.tmdbsamsungtest.ui.base


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.sathvik1709.tmdbsamsungtest.R
import com.sathvik1709.tmdbsamsungtest.adapter.MoviesRecyclerViewAdapter
import com.sathvik1709.tmdbsamsungtest.dto.Movie
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import com.sathvik1709.tmdbsamsungtest.ui.activities.MovieDetailActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_movies_list.*

abstract class BaseMoviesListFragment : Fragment() {

    abstract fun getRequestPath(): String
    abstract fun getPresenter(): MoviesListContract.Presenter

    lateinit var moviesListRecyclerView: RecyclerView
    lateinit var disposable: Disposable
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_movies_list, container, false)

        moviesListRecyclerView = view.findViewById(R.id.movies_recycler_view)
        progressBar = view.findViewById(R.id.movies_fragment_progress_bar)

        getPresenter().getMoviesList(getRequestPath())

        return view
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun setUpRecyclerView(list: List<Movie>) {
        var adapter: MoviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(list)

        disposable = (adapter as MoviesRecyclerViewAdapter).onViewClicked.subscribe({
            movie ->
            run {
                val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                    putExtra("SELECTED_MOVIE", movie)
                }
                startActivity(intent)
            }
        })

        moviesListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        moviesListRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }


}