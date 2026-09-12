package com.saurav.cinemax.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val rating: Double,
    val voteCount: Int,
    val popularity: Double,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val adult: Boolean,
    val video: Boolean
)