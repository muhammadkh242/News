package com.example.news.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.RepositoryInterface
import com.example.news.repository.model.APIResponse
import com.example.news.repository.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val _repo: RepositoryInterface): ViewModel() {

    init {
        Log.i("TAG", "VIEW MODEL INITIALIZER")
        getNewsObject()
    }
    private var _news: MutableLiveData<APIResponse> = MutableLiveData()
    var news: LiveData<APIResponse> = _news


    fun getNewsObject(){
        viewModelScope.launch {
            val newsResponse = _repo.getAllNews()
            withContext(Dispatchers.IO){
                Log.i("TAG", "getNewsObject: ${newsResponse}")
                _news.postValue(newsResponse)
            }
        }
    }

    /*    fun getAllMovies(){
        viewModelScope.launch{
            val movies = _iRepo.getAllMovies()
            withContext(Dispatchers.Main){
                Log.i(TAG, "getAllMovies: ${movies}")
                _movieList.postValue(movies)
            }
        }
    }*/
}