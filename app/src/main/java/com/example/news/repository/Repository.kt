package com.example.news.repository

import android.content.Context
import com.example.news.network.RemoteSource
import com.example.news.repository.model.APIResponse



class Repository(var context: Context, var remoteSource: RemoteSource): RepositoryInterface {


    companion object {
        private var repo: Repository? = null

        fun getInstance(
            context: Context,
            remoteSource: RemoteSource,
        ): Repository {
            if (repo == null) {
                repo = Repository(context, remoteSource)
            }
            return repo!!
        }

    }
    override suspend fun getNewsObject(category: String): APIResponse {
        return remoteSource.getNewsObject(category = category)
    }

    override suspend fun getSearchResult(q: String): APIResponse {
        return remoteSource.getSearchResult(q = q)
    }


}