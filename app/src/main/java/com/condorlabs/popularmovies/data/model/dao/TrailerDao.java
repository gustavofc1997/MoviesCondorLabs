package com.condorlabs.popularmovies.data.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.condorlabs.popularmovies.data.model.entity.Trailer;

import java.util.List;

@Dao
public interface TrailerDao {

    @Query("SELECT * FROM trailer WHERE movieId=:id")
    LiveData<List<Trailer>> loadTrailersByMovie(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTrailers(List<Trailer> movieEntityEntities);


}
