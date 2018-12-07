package com.condorlabs.popularmovies.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.condorlabs.popularmovies.data.Resource;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.data.remote.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gustavofc97 on 9/11/2018.
 */

public class MovieListViewModel extends ViewModel {


    private final LiveData<Resource<List<Movie>>> mPopularMovies;

    @Inject
    MovieListViewModel(MovieRepository movieRepository) {
        mPopularMovies = movieRepository.loadPopularMovies();
    }

    LiveData<Resource<List<Movie>>> getmPopularMovies() {
        return mPopularMovies;
    }
}
