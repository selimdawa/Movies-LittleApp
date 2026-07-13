package com.littleapp.movies.data.room.repository

import androidx.lifecycle.LiveData
import com.littleapp.movies.data.room.dao.MoviesDao
import com.littleapp.movies.models.MovieItemModel
import javax.inject.Inject

class MoviesRepositoryRealization @Inject constructor(private val moviesDao: MoviesDao): MoviesRepository {

    override val allMovies: LiveData<List<MovieItemModel>>
        get() = moviesDao.getAllMovies()

    override suspend fun insertMovie(movieItemModel: MovieItemModel) {
        moviesDao.insert(movieItemModel)
    }

    override suspend fun deleteMovie(movieItemModel: MovieItemModel) {
        moviesDao.delete(movieItemModel)
    }
}