package com.example.news.network

import android.util.Log
import com.example.news.repository.model.APIResponse

class NewsClient: RemoteSource{
    private val TAG = "TAG"
    override suspend fun getNewsObject(category: String): APIResponse {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        Log.i(TAG, "getAllNews: ${newsService.getNewsObject().articles.size}")
        return newsService.getNewsObject(category = category)
    }


    companion object{
        private var instance: NewsClient? = null
        fun getInstance(): NewsClient {
            return instance?: NewsClient()
        }
    }
}