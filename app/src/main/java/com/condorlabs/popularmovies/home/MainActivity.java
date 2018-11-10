package com.condorlabs.popularmovies.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.condorlabs.popularmovies.R;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.databinding.ActivityMainBinding;
import com.condorlabs.popularmovies.moviedetail.MovieDetailActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by gustavofc97 on 9/11/2018.
 */

public class MainActivity extends AppCompatActivity implements MovieItemCallback {

    MovieListViewModel mMovieListViewModel;
    ActivityMainBinding mDataBinding;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMovieListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieListViewModel.class);

        mDataBinding.rvMovies.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rvMovies.setAdapter(new MovieListAdapter(this));
        loadMoviesDefault();
    }

    @Override
    public void onClickMovieItem(Movie movie, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, sharedView, getString(R.string.shared_image));
        startActivity(MovieDetailActivity.newIntent(this, movie.getId()), options.toBundle());
    }


    private void loadMoviesDefault() {
        mMovieListViewModel.getmPopularMovies()
                .observe(this, listResource -> {
                    mDataBinding.setResource(listResource);
                });

    }
}
