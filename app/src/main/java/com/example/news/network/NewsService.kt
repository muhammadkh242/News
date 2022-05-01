package com.example.news.network

import com.example.news.repository.model.APIResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getNewsObject(
        @Query("category") category:String = "general",
        @Query("country") country:String = "us",
        @Query("apiKey") app_id: String = "e9e48500298a4b6f853c52fb015b12ac"
    ): APIResponse


    @GET("everything")
    suspend fun getSearchResult(
        @Query("q") q:String,
        @Query("sortBy") sortBy:String = "publishedAt",
        @Query("apiKey") app_id: String = "e9e48500298a4b6f853c52fb015b12ac"
    ): APIResponse

}