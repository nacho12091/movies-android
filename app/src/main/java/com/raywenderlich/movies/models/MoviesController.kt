package com.raywenderlich.movies.models

class MoviesResponse(
    var page: Int,
    var totalResults: Int,
    var totalPages: Int,
    var results: ArrayList<MovieResult>
) {

    fun getMoviesResult(): ArrayList<MovieResult> {
        return this.results
    }

}

class MovieResult(
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var poster_path: String,
    var id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var genre_ids: List<Int>,
    var vote_average: Double,
    var overview: String,
    var release_date: String,
    var title: String
) {}