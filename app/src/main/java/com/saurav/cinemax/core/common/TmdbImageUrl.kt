package com.saurav.cinemax.core.common

fun getTmdbPosterUrl(posterPath: String?): String? {
    return posterPath?.let {
        "https://image.tmdb.org/t/p/w500$it"
    }
}