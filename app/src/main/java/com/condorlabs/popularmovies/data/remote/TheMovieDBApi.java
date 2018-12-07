package com.condorlabs.popularmovies.data.remote;


import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.data.remote.model.MoviesResponse;
import com.condorlabs.popularmovies.data.remote.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by gustavofc97 on 9/11/2018.
 */

public interface TheMovieDBApi {

    String URL_BASE = "https://api.themoviedb.org/";
    String API_KEY = "6d1b16b829110acfda3c18079e022ff3";

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY)
    Call<MoviesResponse> getPopularMovies();

    @GET("3/movie/{Id}/videos?api_key=" + API_KEY)
    Call<TrailersResponse> getMovieVideoId(@Path("Id") String movieId);

    @GET("3/movie/{Id}?api_key=" + API_KEY)
    Call<Movie> getMovieDetails(@Path("Id") String movieId);

}
