package com.condorlabs.popularmovies.home

import com.condorlabs.popularmovies.data.remote.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class MoviesHomeModule {

    @Provides
    fun providesPresenter(repository: MovieRepository): MoviesHomeContract.MoviesHomePresenter {
        return MoviesHomePresenterImpl(repository)
    }

}