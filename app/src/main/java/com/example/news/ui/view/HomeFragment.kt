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
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.APIResponse
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory
import com.example.news.utils.Connection
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private val TAG = "TAG"
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance()),requireActivity().application) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }
    private val defaultPref by lazy{ PreferenceManager.getDefaultSharedPreferences(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpRecyclerView()

        observeNews()

        handleSearchView()

        handleRefresher()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeNews()
    }



    private fun setUpRecyclerView() = binding.apply {
        newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        newsRecycler.adapter = NewsAdapter(requireContext())
    }

    private fun observeNews(){
        if(Connection.isOnline(requireContext())){
            binding.apply {
                noConnection.visibility = View.GONE
                binding.card.visibility = View.VISIBLE
                searchView.visibility = View.VISIBLE
            }
            viewModel.checkCountry()
            viewModel.getNews()
            viewModel.news.observe(viewLifecycleOwner) {
                fillNewsData(it)
                binding.progressBar.visibility = ProgressBar.INVISIBLE
                binding.refresher.isRefreshing = false
            }
        }else{
            binding.apply {
                noConnection.visibility = View.VISIBLE
                noConnection.setImageResource(R.drawable.wifi)
                binding.card.visibility = View.INVISIBLE
                searchView.visibility = View.INVISIBLE
                progressBar.visibility = ProgressBar.INVISIBLE
                refresher.isRefreshing = false
            }
            Toast.makeText(requireContext(), "Not Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fillNewsData(apiResponse: APIResponse) = binding.apply {
        (newsRecycler.adapter as NewsAdapter).setData(apiResponse.articles)
    }

    private fun handleSearchView() = binding.searchView.apply {
        var job: Job?= null

        setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(500L)
                    if(p0!!.isNotEmpty()){
                        viewModel.getSearchResult(p0,
                            defaultPref.getString("search", "publishedAt")!!
                        )
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
        setColorSchemeColors(resources.getColor(R.color.my_primary,null))
        setOnRefreshListener {
            isRefreshing = true
            observeNews()
        }
    }
   /* private fun refreshFragment() {
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.detach(this).attach(this).commit()
    }*/

    //private fun checkConnection(): Boolean = Connection.isOnline(requireContext())

}