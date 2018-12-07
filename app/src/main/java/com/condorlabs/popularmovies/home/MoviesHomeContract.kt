package com.condorlabs.popularmovies.home

import com.condorlabs.popularmovies.data.model.entity.Movie
import java.util.*

interface MoviesHomeContract {


    interface MoviesHomeView {
        fun showMoviesList(movieEntities: ArrayList<Movie>)

    }

    interface MoviesHomePresenter {

        fun setView(view: MoviesHomeView)

        fun openMovieDetail(movieId: Int)

        fun loadMoviesList()


    }

}