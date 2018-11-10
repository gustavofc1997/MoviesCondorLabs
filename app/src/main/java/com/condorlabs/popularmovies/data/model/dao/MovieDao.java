package com.condorlabs.popularmovies.data.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.condorlabs.popularmovies.data.model.entity.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    LiveData<List<Movie>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movieEntities);

    @Query("SELECT * FROM Movie WHERE id=:id")
    LiveData<Movie> getMovie(int id);

    @Query("UPDATE Movie SET Favorite = :favMovie WHERE id = :id")
    void updateMovieStatus(int id, Boolean favMovie);

    @Query("UPDATE Movie SET budget = :budget WHERE id = :id")
    void updateMovieBudget(int id, int budget);
}
