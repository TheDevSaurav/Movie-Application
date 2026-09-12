package com.saurav.cinemax.domain.usecase

import androidx.paging.PagingData
import com.saurav.cinemax.domain.model.Movie
import com.saurav.cinemax.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor (
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}