package com.littleapp.movies.models

sealed class MoviesUiState {
    object Loading : MoviesUiState()
    data class Success(val movies: List<MovieItemModel>) : MoviesUiState()
    data class Error(val message: String) : MoviesUiState()
}