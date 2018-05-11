package com.sathvik1709.tmdbsamsungtest.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sathvik1709.tmdbsamsungtest.ui.now_playing.NowPlayingView
import com.sathvik1709.tmdbsamsungtest.ui.upcoming.UpcomingMoviesView

class MoviesViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return NowPlayingView()
            1 -> return UpcomingMoviesView()
        }
        return null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Now Playing"
            1 -> return "Upcoming"
        }
        return ""
    }

    override fun getCount(): Int = 2

}