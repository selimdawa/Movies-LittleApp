package com.littleapp.movies.data.retrofit

import com.littleapp.movies.data.retrofit.api.ApiService
import com.littleapp.movies.models.MoviesModel
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovie(): Response<MoviesModel> {
        return apiService.getPopularMovie()
    }
}