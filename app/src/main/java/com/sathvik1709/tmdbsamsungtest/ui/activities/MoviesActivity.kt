package com.sathvik1709.tmdbsamsungtest.ui.activities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.sathvik1709.tmdbsamsungtest.R
import com.sathvik1709.tmdbsamsungtest.adapter.MoviesViewPagerAdapter
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject



class MoviesActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var moviesViewPager : ViewPager
    private lateinit var moviesTabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesViewPager = movies_activity_tab_view_pager
        moviesTabLayout = movies_activity_tab_layout

        setUpViewPager()
    }

    private fun setUpViewPager(){
        val moviesViewPagerAdapter = MoviesViewPagerAdapter(supportFragmentManager)
        moviesViewPager.adapter = moviesViewPagerAdapter
        moviesTabLayout.setupWithViewPager(moviesViewPager)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
