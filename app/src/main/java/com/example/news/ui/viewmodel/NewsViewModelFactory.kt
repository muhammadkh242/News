package com.example.news.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.repository.RepositoryInterface

class NewsViewModelFactory(private val _repo: RepositoryInterface, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            NewsViewModel(_repo, application) as T
        } else {
            throw IllegalArgumentException("not found view model class")
        }

    }
}