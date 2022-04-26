package com.example.news.repository

import com.example.news.repository.model.APIResponse
import com.example.news.repository.model.News

interface RepositoryInterface {
    suspend fun getAllNews(): APIResponse
}