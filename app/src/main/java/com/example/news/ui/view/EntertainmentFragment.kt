package com.example.news.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentEntertainmentBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory
import com.example.news.utils.Connection


class EntertainmentFragment : Fragment() {
    private val binding by lazy { FragmentEntertainmentBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance()),
        requireActivity().application) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setUpRecyclerView()
        observeNews()
        handleRefresher()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeNews()
    }
    private fun setUpRecyclerView() = binding.apply {
        entertainmentRecycler.layoutManager = LinearLayoutManager(requireContext())
        entertainmentRecycler.adapter = NewsAdapter(requireContext())
    }

    private fun observeNews(){
        if(Connection.isOnline(requireContext())){
            viewModel.checkCountry()
            viewModel.getEntertainmentNews()
            viewModel.entertainmentNews.observe(viewLifecycleOwner) {
                fillNewsData(it)
                binding.progressBar.visibility = ProgressBar.INVISIBLE
                binding.refresher.isRefreshing = false

            }

        }
        else{
            binding.refresher.isRefreshing = false
            binding.progressBar.visibility = ProgressBar.INVISIBLE
            Toast.makeText(requireContext(), "Not Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (entertainmentRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }

    private fun handleRefresher() = binding.refresher.apply {
        setColorSchemeColors(resources.getColor(R.color.my_primary,null))
        setOnRefreshListener {
            isRefreshing = true
            observeNews()
        }
    }
}