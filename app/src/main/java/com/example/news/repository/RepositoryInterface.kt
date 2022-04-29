package com.example.news.repository

import com.example.news.repository.model.APIResponse

interface RepositoryInterface {
    suspend fun getNewsObject(category: String): APIResponse
    suspend fun getSearchResult(q: String): APIResponse

}