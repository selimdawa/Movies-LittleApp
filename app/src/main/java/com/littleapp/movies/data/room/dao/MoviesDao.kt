package com.littleapp.movies.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.littleapp.movies.models.MovieItemModel

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(moviesItemModel: MovieItemModel)

    @Delete
    fun delete(moviesItemModel: MovieItemModel)

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<MovieItemModel>>
}