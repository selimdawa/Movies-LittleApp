package com.littleapp.movies.data.room.repository

import androidx.lifecycle.LiveData
import com.littleapp.movies.models.MovieItemModel

interface MoviesRepository {
    val allMovies: LiveData<List<MovieItemModel>>
    suspend fun insertMovie(movieItemModel: MovieItemModel)
    suspend fun deleteMovie(movieItemModel: MovieItemModel)
}