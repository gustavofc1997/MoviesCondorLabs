package com.condorlabs.popularmovies.data.remote.model;

import com.condorlabs.popularmovies.data.model.entity.Trailer;

import java.util.ArrayList;

public class TrailersResponse {

    private ArrayList<Trailer> results;

    public ArrayList<Trailer> getResults() {
        return results;
    }

}
