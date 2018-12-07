package com.condorlabs.popularmovies.moviedetail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.condorlabs.popularmovies.data.model.entity.Movie
import com.condorlabs.popularmovies.databinding.ActivityMovieDetailBinding
import com.condorlabs.popularmovies.util.AppConstants
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import dagger.android.AndroidInjection
import com.condorlabs.popularmovies.R
import javax.inject.Inject

/**
 * Created by gustavofc97 on 9/11/2018.
 */


class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.MovieDetailView, YouTubePlayer.PlaybackEventListener, YouTubePlayer.OnInitializedListener {


    @Inject
    lateinit var mPresenter: MovieDetailContract.MovieDetailPresenter
    private val mTrailerId: String? = null
    private lateinit var mActivityBinding: ActivityMovieDetailBinding
    private var mMovieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(getString(R.string.title_movie_detail))
        }

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        mMovieId = intent.getIntExtra(PARAM_MOVIE_ID, 0)

        mPresenter.setView(this)
        mPresenter.loadMovieDetail(mMovieId)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> ActivityCompat.finishAfterTransition(this)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean) {
        if (!wasRestored) {
            val style = YouTubePlayer.PlayerStyle.MINIMAL
            youTubePlayer.setPlayerStyle(style)
            youTubePlayer.cueVideo(mTrailerId)
        }


    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {

    }

    override fun onPlaying() {

    }

    override fun onPaused() {

    }

    override fun onStopped() {

    }

    override fun onBuffering(b: Boolean) {

    }

    override fun onSeekTo(i: Int) {

    }

    override fun showNoMessageNoVideoAvailable() {

    }

    override fun markMovieAsFavorite() {

    }

    override fun removeMovieFromFavorites() {

    }

    override fun setMovie(movie: Movie) {
        mActivityBinding.movie = movie
    }


    private fun initYTplayer() {
        val mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(AppConstants.DEVELOPER_KEY_YOUTUBE, this)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment)
        fragmentTransaction.commit()
    }

    companion object {

        const val PARAM_MOVIE_ID = "param_movie_id"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(PARAM_MOVIE_ID, movieId)
            return intent
        }
    }
}
