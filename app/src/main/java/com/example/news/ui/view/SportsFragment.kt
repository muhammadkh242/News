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
import com.example.news.databinding.FragmentScienceBinding
import com.example.news.databinding.FragmentSportsBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory

class SportsFragment : Fragment() {
    private val binding by lazy { FragmentSportsBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpRecyclerView()
        viewModel.getSportsNews()
        observeNews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSportsNews()
        observeNews()
    }

    private fun setUpRecyclerView() = binding.apply {
        sportsRecycler.layoutManager = LinearLayoutManager(requireContext())
        sportsRecycler.adapter = NewsAdapter(requireContext())
    }
    private fun observeNews(){
        viewModel.sportsNews.observe(viewLifecycleOwner) {
            fillNewsData(it)
            binding.progressBar.visibility = ProgressBar.INVISIBLE

        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (sportsRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }

}