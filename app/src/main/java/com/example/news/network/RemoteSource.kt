package com.example.news.network

import com.example.news.repository.model.APIResponse
import retrofit2.Response

interface RemoteSource {
    suspend fun getNewsObject(category: String): APIResponse
}