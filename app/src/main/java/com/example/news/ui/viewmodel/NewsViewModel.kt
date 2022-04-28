package com.example.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.RepositoryInterface
import com.example.news.repository.model.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val _repo: RepositoryInterface): ViewModel() {
    private val TAG = "TAG"

    private var _news: MutableLiveData<APIResponse> = MutableLiveData()
    var news: LiveData<APIResponse> = _news

    private var _sciNews: MutableLiveData<APIResponse> = MutableLiveData()
    var sciNews: LiveData<APIResponse> = _sciNews

    private var _businessNews: MutableLiveData<APIResponse> = MutableLiveData()
    var businessNews: LiveData<APIResponse> = _businessNews

    private var _sportsNews: MutableLiveData<APIResponse> = MutableLiveData()
    var sportsNews: LiveData<APIResponse> = _sportsNews

    private var _entertainmentNews: MutableLiveData<APIResponse> = MutableLiveData()
    var entertainmentNews: LiveData<APIResponse> = _entertainmentNews

    private var _healthNews: MutableLiveData<APIResponse> = MutableLiveData()
    var healthNews: LiveData<APIResponse> = _healthNews

    private var _techNews: MutableLiveData<APIResponse> = MutableLiveData()
    var techNews: LiveData<APIResponse> = _healthNews


    fun getNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "general")
            withContext(Dispatchers.IO){
                _news.postValue(newsResponse)
            }
        }
    }

    fun getSciNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "science")
            withContext(Dispatchers.IO){
                _sciNews.postValue(newsResponse)
            }
        }
    }

    fun getBusinessNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "business")
            withContext(Dispatchers.IO){
                _businessNews.postValue(newsResponse)
            }
        }
    }

    fun getSportsNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "sports")
            withContext(Dispatchers.IO){
                _sportsNews.postValue(newsResponse)
            }
        }
    }

    fun getEntertainmentNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "entertainment")
            withContext(Dispatchers.IO){
                _entertainmentNews.postValue(newsResponse)
            }
        }
    }

    fun getHealthNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "health")
            withContext(Dispatchers.IO){
                _healthNews.postValue(newsResponse)
            }
        }
    }

    fun getTechNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "technology")
            withContext(Dispatchers.IO){
                _techNews.postValue(newsResponse)
            }
        }
    }
}