package com.saurav.cinemax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.saurav.cinemax.BuildConfig
import com.saurav.cinemax.data.remote.api.MovieApi
import com.saurav.cinemax.data.remote.paging.MoviePagingSource
import com.saurav.cinemax.domain.model.Movie
import com.saurav.cinemax.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieApi = movieApi,
                    apiKey = BuildConfig.TMDB_API_KEY
                )
            }
        ).flow
    }
}