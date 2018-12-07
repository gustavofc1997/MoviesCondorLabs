package com.condorlabs.popularmovies.di;

import com.condorlabs.popularmovies.home.MainActivity;
import com.condorlabs.popularmovies.home.MoviesHomeModule;
import com.condorlabs.popularmovies.moviedetail.MovieDetailActivity;
import com.condorlabs.popularmovies.moviedetail.MovieDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MoviesHomeModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = MovieDetailModule.class)
    abstract MovieDetailActivity movieDetailActivity();


}
