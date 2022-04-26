package com.example.news.network

import com.example.news.repository.model.News
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllNews(): News
}