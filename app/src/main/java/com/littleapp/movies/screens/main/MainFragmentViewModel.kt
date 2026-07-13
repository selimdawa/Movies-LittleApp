package com.littleapp.movies.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littleapp.movies.data.retrofit.RetrofitRepository
import com.littleapp.movies.models.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: RetrofitRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<MoviesUiState>()
    val uiState: LiveData<MoviesUiState> get() = _uiState

    init {
        getMoviesRetrofit()
    }

    private fun getMoviesRetrofit() {
        viewModelScope.launch {
            _uiState.value = MoviesUiState.Loading
            try {
                val response = repository.getMovie()
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    _uiState.value = MoviesUiState.Success(movies)
                } else {
                    _uiState.value = MoviesUiState.Error("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = MoviesUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}