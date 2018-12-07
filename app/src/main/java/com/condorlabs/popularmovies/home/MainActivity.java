package com.condorlabs.popularmovies.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.condorlabs.popularmovies.R;
import com.condorlabs.popularmovies.data.model.entity.Movie;
import com.condorlabs.popularmovies.databinding.ActivityMainBinding;
import com.condorlabs.popularmovies.moviedetail.MovieDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dagger.android.AndroidInjection;


/**
 * Created by gustavofc97 on 9/11/2018.
 */


public class MainActivity extends AppCompatActivity implements MovieItemCallback, MoviesHomeContract.MoviesHomeView {

    MovieListViewModel mMovieListViewModel;
    ActivityMainBinding mDataBinding;
    MovieListAdapter mListAdapter;
    private List<Movie> mListMoviews;


    // @Inject
    //MoviesHomeContract.MoviesHomePresenter mPresenter;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        //  ((MoviesApp) getApplication()).getComponent().inject(this);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // mPresenter.setView(this);

        mListAdapter = new MovieListAdapter(this);
        mDataBinding.rvMovies.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rvMovies.setAdapter(mListAdapter);

//        mPresenter.loadMoviesList();

        /*mMovieListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieListViewModel.class);
        mDataBinding.setMovieListViewModel(mMovieListViewModel);

        loadMoviesDefault();
        mDataBinding.spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mListAdapter.setData(mListMoviews);
                        break;
                    case 1:
                        mListAdapter.filterByFav(mListMoviews);
                        break;
                    case 2:
                        mListAdapter.filterByMostVoted(mListMoviews);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

    @Override
    public void onClickMovieItem(Movie movie, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, sharedView, getString(R.string.shared_image));
        startActivity(MovieDetailActivity.Companion.newIntent(this, movie.getId()), options.toBundle());
    }


    private void loadMoviesDefault() {
      /*  mMovieListViewModel.getmPopularMovies()
                .observe(this, listResource -> {
                    mListMoviews = listResource.data;
                    mDataBinding.setResource(listResource);
                });*/

    }


    @Override
    public void showMoviesList(@NotNull ArrayList<Movie> movieEntities) {
        mListAdapter.setData(movieEntities);
    }
}
