package com.condorlabs.popularmovies.moviedetail

import com.condorlabs.popularmovies.data.remote.MovieRepository


class MovieDetailPresenterImpl(val movieRepository: MovieRepository) : MovieDetailContract.MovieDetailPresenter {

    var mView: MovieDetailContract.MovieDetailView? = null


    override fun setView(view: MovieDetailContract.MovieDetailView) {
        mView = view
    }


    override fun loadMovieTrailer(movieId: String) {

    }

    override fun loadMovieDetail(movieId: Int) {

    }


}

