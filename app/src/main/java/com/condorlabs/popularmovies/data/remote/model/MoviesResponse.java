package com.condorlabs.popularmovies.data.remote.model;

import com.condorlabs.popularmovies.data.model.entity.Movie;

import java.util.ArrayList;

public class MoviesResponse {
    private ArrayList<Movie> results;


    public ArrayList<Movie> getResults() {
        return results;
    }

}
