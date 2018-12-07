package com.condorlabs.popularmovies.home

import android.view.View

import com.condorlabs.popularmovies.data.model.entity.Movie

/**
 * Created by gustavofc97 on 9/11/2018.
 */

interface MovieItemCallback {
    fun onClickMovieItem(movie: Movie, sharedView: View)
}
