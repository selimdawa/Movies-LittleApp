package com.littleapp.movies.data.retrofit.api

import com.littleapp.movies.models.MoviesModel
import com.littleapp.movies.utils.DATA
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(DATA.POPULAR_MOVIES)
    suspend fun getPopularMovie(): Response<MoviesModel>
}