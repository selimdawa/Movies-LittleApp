package com.littleapp.movies.data.retrofit

import com.littleapp.movies.data.retrofit.api.RetrofitInstance
import com.littleapp.movies.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovie(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}