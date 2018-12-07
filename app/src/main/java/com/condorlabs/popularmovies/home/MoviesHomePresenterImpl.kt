package com.condorlabs.popularmovies.home

import com.condorlabs.popularmovies.data.model.entity.Movie
import com.condorlabs.popularmovies.data.remote.MovieRepository
import java.util.*

class MoviesHomePresenterImpl(val movieRepository: MovieRepository) : MoviesHomeContract.MoviesHomePresenter {

    lateinit var mView: MoviesHomeContract.MoviesHomeView




    override fun setView(view: MoviesHomeContract.MoviesHomeView) {
        mView = view
    }

    override fun openMovieDetail(movieId: Int) {

    }

    override fun loadMoviesList() {
        mView.showMoviesList(movieRepository.loadPopularMovies().value?.data as ArrayList<Movie>)
    }


}