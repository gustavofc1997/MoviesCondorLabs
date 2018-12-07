package com.condorlabs.popularmovies.data.remote;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.condorlabs.popularmovies.data.NetworkBoundResource;
import com.condorlabs.popularmovies.data.Resource;
import com.condorlabs.popularmovies.data.model.dao.MovieDao;
import com.condorlabs.popularmovies.data.model.dao.TrailerDao;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.data.model.entity.Trailer;
import com.condorlabs.popularmovies.data.remote.model.MoviesResponse;
import com.condorlabs.popularmovies.data.remote.model.TrailersResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class MovieRepository {

    private final MovieDao mMovieDao;
    private final TrailerDao mTrailerDao;
    private final com.condorlabs.popularmovies.data.remote.TheMovieDBApi mApiService;

    @Inject
    public MovieRepository(MovieDao mMovieDao, TrailerDao mTrailerDao, TheMovieDBApi mApiService) {
        this.mMovieDao = mMovieDao;
        this.mTrailerDao = mTrailerDao;
        this.mApiService = mApiService;
    }


    public void updateMovieStatus(Movie movie, boolean isFav) {
        new updateMovie(mMovieDao, isFav).execute(movie);
    }


    private static class updateMovie extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncMovieDao;
        private boolean markAsFav;

        updateMovie(MovieDao dao, boolean isFav) {
            mAsyncMovieDao = dao;
            markAsFav = isFav;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncMovieDao.updateMovieStatus(params[0].getId(), markAsFav);
            return null;
        }
    }

    public LiveData<Resource<List<Movie>>> loadPopularMovies() {
        return new NetworkBoundResource<List<Movie>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                mMovieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return mMovieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return mApiService.getPopularMovies();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Trailer>>> getMovieTrailers(String id) {
        return new NetworkBoundResource<List<Trailer>, TrailersResponse>() {

            @Override
            protected void saveCallResult(@NonNull TrailersResponse item) {
                for (Trailer result : item.getResults()) {
                    result.setMovieId(id);
                }
                mTrailerDao.saveTrailers(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Trailer>> loadFromDb() {
                return mTrailerDao.loadTrailersByMovie(id);
            }

            @NonNull
            @Override
            protected Call<TrailersResponse> createCall() {
                return mApiService.getMovieVideoId(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Movie>> getMovieDetail(int id) {
        return new NetworkBoundResource<Movie, Movie>() {

            @Override
            protected void saveCallResult(@NonNull Movie item) {
                mMovieDao.updateMovieBudget(id, item.getBudget());
            }

            @NonNull
            @Override
            protected LiveData<Movie> loadFromDb() {
                return mMovieDao.getMovie(id);
            }

            @NonNull
            @Override
            protected Call<Movie> createCall() {
                return mApiService.getMovieDetails(String.valueOf(id));
            }
        }.getAsLiveData();
    }


}
