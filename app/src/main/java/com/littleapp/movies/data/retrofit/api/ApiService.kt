package com.littleapp.movies.data.retrofit.api

import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(DATA.popular_MOVIES)
    suspend fun getPopularMovie(): Response<MoviesModel>
}