package com.example.news.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.databinding.ActivitySearchBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory

class SearchActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(this, NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(this, factory)[NewsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecyclerView()

        val searchWord = intent.getStringExtra("search_word")
        viewModel.getSearchResult(searchWord!!)

        observeNews()

    }

    private fun setUpRecyclerView() = binding.apply {
        searchRecycler.layoutManager = LinearLayoutManager(this@SearchActivity)
        searchRecycler.adapter = NewsAdapter(this@SearchActivity)
    }
    private fun observeNews(){
        viewModel.searchNews.observe(this) {
            fillNewsData(it)
            binding.progressBar.visibility = ProgressBar.INVISIBLE

        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (searchRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }
}