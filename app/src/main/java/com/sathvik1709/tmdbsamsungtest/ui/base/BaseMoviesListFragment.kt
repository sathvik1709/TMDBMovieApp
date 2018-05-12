package com.sathvik1709.tmdbsamsungtest.ui.base


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
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
import com.sathvik1709.tmdbsamsungtest.extensions.Util
import com.sathvik1709.tmdbsamsungtest.ui.MoviesListContract
import com.sathvik1709.tmdbsamsungtest.ui.activities.MovieDetailActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import android.R.string.ok
import android.R.string.ok
import java.util.ArrayList


abstract class BaseMoviesListFragment : Fragment() {

    abstract fun getRequestPath(): String
    abstract fun getPresenter(): MoviesListContract.Presenter

    lateinit var moviesListRecyclerView: RecyclerView
    lateinit var disposable: Disposable
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var util: Util

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_movies_list, container, false)

        moviesListRecyclerView = view.findViewById(R.id.movies_recycler_view)
        progressBar = view.findViewById(R.id.movies_fragment_progress_bar)

        callMovieList()

        return view
    }

    fun callMovieList() = getPresenter().getMoviesList(getRequestPath())

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun setUpRecyclerView(list: List<Movie>) {
        var adapter: MoviesRecyclerViewAdapter = MoviesRecyclerViewAdapter(list, util)

        disposable = (adapter as MoviesRecyclerViewAdapter).onViewClicked.subscribe({
            movie ->
            run {
                val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                    // This can be improved using parcelable
                    putExtra("RELEASE_DATE", movie.release_date)
                    putExtra("VOTES", movie.vote_count)
                    putStringArrayListExtra("GENRES", movie.genre_names as ArrayList<String>?)
                    putExtra("OVERVIEW", movie.overview)
                    putExtra("MOVIE_TITLE", movie.title)
                    putExtra("POSTER", movie.poster_path)
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

    fun showErrorDialog(errorMsg : String){
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(errorMsg)
                .setTitle("Error")
        builder.setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
            callMovieList()
        })
        builder.setNegativeButton("Close", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
        })
        val dialog = builder.create()
        dialog.show()
    }


}