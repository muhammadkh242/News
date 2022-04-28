package com.example.news.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentEntertainmentBinding
import com.example.news.databinding.FragmentSportsBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory


class EntertainmentFragment : Fragment() {
    private val binding by lazy { FragmentEntertainmentBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()
        viewModel.getEntertainmentNews()
        observeNews()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEntertainmentNews()
        observeNews()
    }
    private fun setUpRecyclerView() = binding.apply {
        entertainmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        entertainmentRecycler.adapter = NewsAdapter(requireContext())
    }
    private fun observeNews(){
        viewModel.entertainmentNews.observe(viewLifecycleOwner) {
            fillNewsData(it)
        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (entertainmentRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }
}