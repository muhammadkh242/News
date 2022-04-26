package com.example.news.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.network.NewsClient
import com.example.news.repository.Repository
import com.example.news.repository.model.News
import com.example.news.ui.viewmodel.NewsViewModel
import com.example.news.ui.viewmodel.NewsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private val TAG = "TAG"
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val factory by lazy { NewsViewModelFactory(Repository.getInstance(requireContext(), NewsClient.getInstance())) }
    private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.news.observe(viewLifecycleOwner) {
            binding.textView.text = it.toString()

            Log.i(TAG, "response test: ${it.toString()}")

        }
        /*GlobalScope.launch(Dispatchers.Main) {
            val res = NewsClient.getInstance().getAllNews()
            Log.i("TAG", "RESPONSE BODY TOTAL: ${res.toString()}")

        }*/
        return binding.root
    }



}