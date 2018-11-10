package com.condorlabs.popularmovies.di;

import com.condorlabs.popularmovies.home.MainActivity;
import com.condorlabs.popularmovies.moviedetail.MovieDetailActivity;
import com.condorlabs.popularmovies.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector()
    abstract MovieDetailActivity movieDetailActivity();

    @ContributesAndroidInjector()
    abstract SplashActivity splashActivity();
}
