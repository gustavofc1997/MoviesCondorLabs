package com.condorlabs.popularmovies.di

import android.app.Application
import android.arch.persistence.room.Room
import com.condorlabs.popularmovies.data.model.MoviesDatabase
import com.condorlabs.popularmovies.data.model.dao.MovieDao
import com.condorlabs.popularmovies.data.model.dao.TrailerDao
import com.condorlabs.popularmovies.data.remote.MovieRepository
import com.condorlabs.popularmovies.data.remote.TheMovieDBApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by gustavofc97 on 9/11/2018.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): TheMovieDBApi {
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(TheMovieDBApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(TheMovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MoviesDatabase {
        return Room.databaseBuilder(application, MoviesDatabase::class.java, "moviesApp.db").build()
    }

    @Provides
    fun provideMovieDao(moviesDatabase: MoviesDatabase): MovieDao {
        return moviesDatabase.movieDao()
    }

    @Provides
    fun provideMovieRepository(movieDao: MovieDao, trailerDao: TrailerDao, apiService: TheMovieDBApi): MovieRepository {
        return MovieRepository(movieDao, trailerDao, apiService)
    }

    @Provides
    fun provideTrailerDao(moviesDatabase: MoviesDatabase): TrailerDao {
        return moviesDatabase.trailerDao()
    }

}
