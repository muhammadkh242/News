package com.example.news.network

import com.example.news.repository.model.APIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getNewsObject(
        @Query("category") category:String = "general",
        @Query("country") country:String = "us",
        @Query("apiKey") app_id: String = "3b137ac75fc84df8acd829c8baaf9509"
    ): APIResponse


    @GET("everything")
    suspend fun getSearchResult(
        @Query("q") q:String,
        @Query("apiKey") app_id: String = "3b137ac75fc84df8acd829c8baaf9509"
    ): APIResponse

}