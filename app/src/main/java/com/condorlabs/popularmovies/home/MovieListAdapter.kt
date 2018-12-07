package com.condorlabs.popularmovies.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.condorlabs.popularmovies.BaseAdapter
import com.condorlabs.popularmovies.data.model.entity.Movie
import java.util.*

/**
 * Created by gustavofc97 on 9/11/2018.
 */

class MovieListAdapter(val movieItemCallback: MovieItemCallback) : BaseAdapter<MovieListAdapter.MovieViewHolder, Movie>() {

    private var mMoviesList = listOf<Movie>()


    override fun setData(movieEntities: List<Movie>) {
        this.mMoviesList = movieEntities
        notifyDataSetChanged()
    }

    fun filterByFav(movieEntities: List<Movie>) {
        val moviesToShow = ArrayList<Movie>()
        for (movieEntity in movieEntities) {
            if (movieEntity.isFavorite)
                moviesToShow.add(movieEntity)
        }
        setData(moviesToShow)
    }

    internal fun filterByMostVoted(movieEntities: List<Movie>) {
        val moviesToShow = ArrayList<Movie>()
        for (movieEntity in movieEntities) {
            if (movieEntity.voteCount > 2000)
                moviesToShow.add(movieEntity)
        }
        setData(moviesToShow)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        return MovieViewHolder.create(LayoutInflater.from(viewGroup.context), viewGroup, movieItemCallback)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, i: Int) {
        viewHolder.onBind(mMoviesList[i])
    }

    override fun getItemCount(): Int {
        return mMoviesList.size
    }

    class MovieViewHolder(var binding: ItemMovieListBinding, callback: MovieItemCallback) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { callback.onClickMovieItem(binding.movie, binding.imgPoster) }
        }

        fun onBind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }

        companion object {

            fun create(inflater: LayoutInflater, parent: ViewGroup, callback: MovieItemCallback): MovieViewHolder {
                val itemMovieListBinding = ItemMovieListBinding.inflate(inflater, parent, false)
                return MovieViewHolder(itemMovieListBinding, callback)
            }
        }
    }
}
