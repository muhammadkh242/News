package com.example.news.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory
import androidx.appcompat.widget.SearchView


class HomeFragment : Fragment() {
    private val TAG = "TAG"
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()
        viewModel.getNews()
        observeNews()

        handleSearchView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
        observeNews()
    }

    private fun setUpRecyclerView() = binding.apply {
        newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        newsRecycler.adapter = NewsAdapter(requireContext())
    }

    private fun observeNews(){
        viewModel.news.observe(viewLifecycleOwner) {
            fillNewsData(it)
        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (newsRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }

    fun handleSearchView() = binding.searchView.apply {
        queryHint = "Search"

        setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.i(TAG, "onQueryTextSubmit: $p0")
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.i(TAG, "onQueryTextChange: $p0")
                return false
            }

        })
    }

}