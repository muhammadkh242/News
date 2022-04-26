package com.example.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.repository.RepositoryInterface

class NewsViewModelFactory(private val _repo: RepositoryInterface): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            NewsViewModel(_repo) as T
        } else {
            throw IllegalArgumentException("not found view model class")
        }

    }
}