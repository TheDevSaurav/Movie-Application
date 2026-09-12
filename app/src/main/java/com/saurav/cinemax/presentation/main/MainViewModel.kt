package com.saurav.cinemax.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saurav.cinemax.domain.model.Movie
import com.saurav.cinemax.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val movies: Flow<PagingData<Movie>> = getMoviesUseCase().cachedIn(viewModelScope)
}