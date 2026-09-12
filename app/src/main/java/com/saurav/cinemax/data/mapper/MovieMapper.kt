package com.saurav.cinemax.data.mapper

import com.saurav.cinemax.data.remote.dto.MovieDto
import com.saurav.cinemax.domain.model.Movie

fun MovieDto.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        rating = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        adult = adult,
        video = video
    )
}