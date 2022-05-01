package com.example.news.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.news.R
import com.example.news.repository.RepositoryInterface
import com.example.news.repository.model.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val _repo: RepositoryInterface, private val application: Application): ViewModel() {
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

    var country = PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
        .getString("country", "us")!!

    fun getNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "general", country = country)
            withContext(Dispatchers.IO){
                _news.postValue(newsResponse)
            }
        }
    }

    fun getSciNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "science", country = country)
            withContext(Dispatchers.IO){
                _sciNews.postValue(newsResponse)
            }
        }
    }

    fun getBusinessNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "business", country = country)
            withContext(Dispatchers.IO){
                _businessNews.postValue(newsResponse)
            }
        }
    }

    fun getSportsNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "sports", country = country)
            withContext(Dispatchers.IO){
                _sportsNews.postValue(newsResponse)
            }
        }
    }

    fun getEntertainmentNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "entertainment", country = country)
            withContext(Dispatchers.IO){
                _entertainmentNews.postValue(newsResponse)
            }
        }
    }

    fun getHealthNews(){
        viewModelScope.launch {
            val newsResponse = _repo.getNewsObject(category = "health", country = country)
            withContext(Dispatchers.IO){
                _healthNews.postValue(newsResponse)
            }
        }
    }


    fun getSearchResult(q: String, sortBy: String){
        viewModelScope.launch {
            val newsResponse = _repo.getSearchResult(q = q, sortBy = sortBy)
            withContext(Dispatchers.IO){
                _news.postValue(newsResponse)
            }
        }
    }
    fun checkCountry(){
        country = PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
            .getString("country", "us")!!
    }
}