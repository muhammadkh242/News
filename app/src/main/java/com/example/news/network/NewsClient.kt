package com.example.news.network

import android.util.Log
import com.example.news.repository.model.News
import retrofit2.Call
import retrofit2.Response

class NewsClient: RemoteSource{
    private val TAG = "TAG"
    override suspend fun getAllNews(): News {
        val newsService = RetrofitHelper.getInstance().create(NewsService::class.java)
        Log.i(TAG, "getAllNews: ${newsService.getAllNews().toString()}")
        return newsService.getAllNews()
    }

    companion object{
        private var instance: NewsClient? = null
        fun getInstance(): NewsClient {
            return instance?: NewsClient()
        }
    }
}