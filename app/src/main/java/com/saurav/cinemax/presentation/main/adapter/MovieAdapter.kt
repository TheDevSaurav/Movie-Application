package com.saurav.cinemax.presentation.main.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.saurav.cinemax.core.common.getTmdbPosterUrl
import com.saurav.cinemax.databinding.ItemMovieBinding
import com.saurav.cinemax.domain.model.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
       val movie = getItem(position) ?: return
        holder.bind(movie)
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.ivPoster.load(getTmdbPosterUrl(movie.posterPath))
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
           return oldItem == newItem
        }

    }
}