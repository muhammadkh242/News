package com.example.news.network

import android.util.Log
import com.example.news.repository.model.APIResponse

class NewsClient: RemoteSource{
    private val TAG = "TAG"
    override suspend fun getNewsObject(category: String): APIResponse {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        return newsService.getNewsObject(category = category)
    }

    override suspend fun getSearchResult(q: String): APIResponse {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        Log.i(TAG, "getSearchResult: ${newsService.getNewsObject(q).articles.size}")
        return newsService.getSearchResult(q = q)
    }


    companion object{
        private var instance: NewsClient? = null
        fun getInstance(): NewsClient {
            return instance?: NewsClient()
        }
    }
}