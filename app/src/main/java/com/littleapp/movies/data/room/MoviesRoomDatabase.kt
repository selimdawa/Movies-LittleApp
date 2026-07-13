package com.littleapp.movies.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.littleapp.movies.data.room.dao.MoviesDao
import com.littleapp.movies.models.MovieItemModel

@Database(entities = [MovieItemModel::class], version = 5)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

}