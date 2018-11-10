package com.condorlabs.popularmovies.data.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.condorlabs.popularmovies.data.model.dao.MovieDao;
import com.condorlabs.popularmovies.data.model.dao.TrailerDao;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.data.model.entity.Trailer;

@Database(entities = {Movie.class, Trailer.class}, version = 2, exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract TrailerDao trailerDao();
}
