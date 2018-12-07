package com.condorlabs.popularmovies.moviedetail

import com.condorlabs.popularmovies.data.model.entity.Movie

interface MovieDetailContract {


    interface MovieDetailPresenter {

        fun loadMovieTrailer(movieId: String)

        fun loadMovieDetail(movieId: Int)

        fun setView(view: MovieDetailView)
    }

    interface MovieDetailView {

        fun showNoMessageNoVideoAvailable()

        fun markMovieAsFavorite()

        fun removeMovieFromFavorites()

        fun setMovie(movie: Movie)
    }
}
