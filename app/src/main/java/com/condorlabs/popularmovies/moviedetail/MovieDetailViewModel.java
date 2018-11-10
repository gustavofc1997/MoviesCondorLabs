package com.condorlabs.popularmovies.moviedetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.condorlabs.popularmovies.data.Resource;
import com.condorlabs.popularmovies.data.model.dao.MovieDao;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.data.model.entity.Trailer;
import com.condorlabs.popularmovies.remote.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gustavofc97 on 9/11/2018.
 */

public class MovieDetailViewModel extends ViewModel {
    private final MovieRepository movieRepository;

    @Inject
    MovieDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<Movie>> getMovieById(int id) {
        return movieRepository.getMovieDetail(id);
    }

    LiveData<Resource<List<Trailer>>> getTrailers(String id) {
        return movieRepository.getMovieTrailers(id);
    }

    public void onCheckedChange(Movie movie, boolean check) {
        movieRepository.updateMovieStatus(movie,check);
    }


}
