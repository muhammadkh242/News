package com.example.news.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

        handleRefresher()

        return binding.root
    }

    /*override fun onResume() {
        super.onResume()
        viewModel.getNews()
        observeNews()
    }*/

    private fun setUpRecyclerView() = binding.apply {
        newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        newsRecycler.adapter = NewsAdapter(requireContext())
    }

    private fun observeNews(){
        viewModel.news.observe(viewLifecycleOwner) {
            fillNewsData(it)
            binding.progressBar.visibility = ProgressBar.INVISIBLE
            binding.refresher.isRefreshing = false
        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (newsRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }

    private fun handleSearchView() = binding.searchView.apply {
        var job: Job?= null

        setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                /*var intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra("search_word", p0)
                startActivity(intent)*/

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(500L)
                    if(p0!!.isNotEmpty()){
                        viewModel.getSearchResult(p0)
                    }
                    else{
                        viewModel.getNews()
                    }
                }
                return false
            }

        })
    }
    private fun handleRefresher() = binding.refresher.apply {
        setColorSchemeColors(resources.getColor(R.color.my_primary,null),
            resources.getColor(R.color.nav_bar_start,null))
        setOnRefreshListener {
            isRefreshing = true
            observeNews()
            Toast.makeText(activity, getString(R.string.updated_just_now),Toast.LENGTH_SHORT).show()
        }
    }


}