package com.raywenderlich.movies.models

class Movie(
    val original_title: String,
    val release_date: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val id: Int,
    val score: Double
) {
}