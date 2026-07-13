package com.littleapp.movies.di

import android.content.Context
import androidx.room.Room
import com.littleapp.movies.data.room.MoviesRoomDatabase
import com.littleapp.movies.data.room.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoviesRoomDatabase {
        return Room.databaseBuilder(
            context, MoviesRoomDatabase::class.java, "db"
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideMoviesDao(database: MoviesRoomDatabase): MoviesDao {
        return database.getMovieDao()
    }
}