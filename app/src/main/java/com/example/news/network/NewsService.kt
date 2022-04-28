package com.example.news.network

import com.example.news.repository.model.APIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    //@GET("search")
    @GET("top-headlines")
    suspend fun getNewsObject(
        @Query("category") category:String = "general",
        @Query("apiKey") app_id: String = "a1c2f37b0a744dcf9026ae1e1bcee545"
    ): APIResponse

}