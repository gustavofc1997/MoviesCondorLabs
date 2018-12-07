package com.condorlabs.popularmovies.moviedetail

import com.condorlabs.popularmovies.data.remote.MovieRepository
import dagger.Module
import dagger.Provides


@Module
class MovieDetailModule {

    @Provides
    fun provideMovieDetailPresenter(movieRepository: MovieRepository): MovieDetailContract.MovieDetailPresenter {
        return MovieDetailPresenterImpl(movieRepository)
    }

}
