package com.example.news.network

import com.example.news.repository.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("search")
    suspend fun getAllNews(
        @Query("api-key") app_id: String = "325e2cc4-43db-4722-844d-ddf673c60bf9"
    ): News
}