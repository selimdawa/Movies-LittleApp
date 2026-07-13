package com.littleapp.movies.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littleapp.movies.data.room.repository.MoviesRepository
import com.littleapp.movies.models.MovieItemModel
import com.littleapp.movies.utils.SaveShared
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val saveShared: SaveShared
) : ViewModel() {

    fun isFavorite(movieId: Int): Boolean {
        return saveShared.getFavorite(movieId.toString())
    }

    fun toggleFavorite(movie: MovieItemModel, isCurrentlyFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isCurrentlyFavorite) {
                repository.deleteMovie(movie)
                saveShared.setFavorite(movie.id.toString(), false)
            } else {
                repository.insertMovie(movie)
                saveShared.setFavorite(movie.id.toString(), true)
            }
        }
    }
}