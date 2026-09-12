package com.saurav.cinemax.domain.repository

import androidx.paging.PagingData
import com.saurav.cinemax.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}