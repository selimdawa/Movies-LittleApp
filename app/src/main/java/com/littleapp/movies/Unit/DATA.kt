package com.littleapp.movies.Unit

import com.littleapp.movies.Activity.MainActivity
import com.littleapp.movies.data.room.repository.MoviesRepositoryRealization

object DATA {

    //Other
    const val EMPTY = ""
    const val SPACE = " "
    const val Unknown = "Unknown"

    const val MOVIE = "Movies"
    const val Favorite_movies = "Favorite movies"
    const val BASE_URL_MOVIES = "https://api.themoviedb.org/"
    const val popular_MOVIES =
        "3/movie/popular?api_key=a036dc05c534b0cd90d6e8a8e2bcf871&language=en-US&page=1"
    const val IMAGE_MOVIE = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
    const val IMAGE_MOVIE_BASIC = "https://image.tmdb.org/t/p/w185/"
    lateinit var MAIN: MainActivity
    lateinit var REALIZATION: MoviesRepositoryRealization
    const val Details_Movie = "Details Movie"
}