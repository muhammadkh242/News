package com.example.news.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentHealthBinding
import com.example.news.databinding.FragmentTechnologyBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory

class TechnologyFragment : Fragment() {

    private val binding by lazy { FragmentTechnologyBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()
        viewModel.getTechNews()
        observeNews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTechNews()
        observeNews()
    }
    private fun setUpRecyclerView() = binding.apply {
        techRecycler.layoutManager = LinearLayoutManager(requireContext())
        techRecycler.adapter = NewsAdapter(requireContext())
    }
    private fun observeNews(){
        viewModel.techNews.observe(viewLifecycleOwner) {
            fillNewsData(it)
        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (techRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }
}