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

    private val movies = mutableListOf<Movie>()

    fun submitList(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

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
        Log.d("CINEMAX_MOVIE", "Movie: ${movie.title}")
        Log.d("CINEMAX_MOVIE", "Poster path: ${movie.posterPath}")
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
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