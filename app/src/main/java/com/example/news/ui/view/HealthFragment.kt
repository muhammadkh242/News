package com.example.news.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentHealthBinding
import com.example.news.databinding.FragmentScienceBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory

class HealthFragment : Fragment() {
    private val binding by lazy { FragmentHealthBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()
        viewModel.getHealthNews()
        observeNews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHealthNews()
        observeNews()
    }

    private fun setUpRecyclerView() = binding.apply {
        healthRecycler.layoutManager = LinearLayoutManager(requireContext())
        healthRecycler.adapter = NewsAdapter(requireContext())
    }
    private fun observeNews(){
        viewModel.healthNews.observe(viewLifecycleOwner) {
            fillNewsData(it)
            binding.progressBar.visibility = ProgressBar.INVISIBLE

        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (healthRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }
}