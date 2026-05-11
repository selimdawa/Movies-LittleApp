package com.littleapp.movies.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.littleapp.movies.Unit.DATA.REALIZATION
import com.littleapp.movies.models.MovieItemModel

class FavoriteFragmentViewModel : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}