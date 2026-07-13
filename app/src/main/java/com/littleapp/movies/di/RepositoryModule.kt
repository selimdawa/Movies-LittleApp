package com.littleapp.movies.di

import com.littleapp.movies.data.room.repository.MoviesRepository
import com.littleapp.movies.data.room.repository.MoviesRepositoryRealization
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        moviesRepositoryRealization: MoviesRepositoryRealization
    ): MoviesRepository
}