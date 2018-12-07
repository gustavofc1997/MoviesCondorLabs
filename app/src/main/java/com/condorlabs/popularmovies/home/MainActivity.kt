package com.condorlabs.popularmovies.home

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.condorlabs.popularmovies.R
import com.condorlabs.popularmovies.data.model.entity.Movie
import com.condorlabs.popularmovies.databinding.ActivityMainBinding
import com.condorlabs.popularmovies.moviedetail.MovieDetailActivity

import java.util.ArrayList

import javax.inject.Inject

import dagger.android.AndroidInjection


/**
 * Created by gustavofc97 on 9/11/2018.
 */


class MainActivity : AppCompatActivity(), MovieItemCallback, MoviesHomeContract.MoviesHomeView {

    lateinit var mDataBinding: ActivityMainBinding
    lateinit var mListAdapter: MovieListAdapter
    private val mListMoviews: List<Movie>? = null


    @Inject
    lateinit var mPresenter: MoviesHomeContract.MoviesHomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //  ((MoviesApp) getApplication()).getComponent().inject(this);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mPresenter.setView(this)

        mListAdapter = MovieListAdapter(this)
        mDataBinding.rvMovies.layoutManager = LinearLayoutManager(this)
        mDataBinding.rvMovies.adapter = mListAdapter

        mPresenter.loadMoviesList()

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

    override fun onClickMovieItem(movie: Movie, sharedView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedView, getString(R.string.shared_image))
        startActivity(MovieDetailActivity.newIntent(this, movie.id), options.toBundle())
    }


    private fun loadMoviesDefault() {
        /*  mMovieListViewModel.getmPopularMovies()
                .observe(this, listResource -> {
                    mListMoviews = listResource.data;
                    mDataBinding.setResource(listResource);
                });*/

    }


    override fun showMoviesList(movieEntities: ArrayList<Movie>) {
        mListAdapter.setData(movieEntities)
    }

    companion object {

        fun newIntent(context: Context): Intent {

            return Intent(context, MainActivity::class.java)
        }
    }
}
