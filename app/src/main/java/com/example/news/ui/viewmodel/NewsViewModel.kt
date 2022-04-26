package com.example.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.RepositoryInterface
import com.example.news.repository.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val _repo: RepositoryInterface): ViewModel() {

    init {
        getNewsObject()
    }

    private var _news: MutableLiveData<News> = MutableLiveData()
    var news: LiveData<News> = _news

    fun getNewsObject(){ viewModelScope.launch {
            val newsResponse = _repo.getAllNews()
            withContext(Dispatchers.IO){ _news.postValue(newsResponse) } }
    }
}