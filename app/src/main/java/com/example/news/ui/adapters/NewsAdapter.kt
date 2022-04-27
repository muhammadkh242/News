package com.example.news.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.NewsItemBinding
import com.example.news.repository.model.Articles

class NewsAdapter(private val context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var newsList: List<Articles> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = newsList[position]

        holder.binding.apply {
            title.text = currentItem.title
            name.text = currentItem.source.name
            description.text = currentItem.description
            author.text = currentItem.author
            date.text = currentItem.publishedAt
        }
        Glide.with(context).load(currentItem.urlToImage).centerCrop().into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(articles: List<Articles>){
        this.newsList = articles
        notifyDataSetChanged()
        Log.i("TAG", "setData: ${newsList.size}")
    }

    class ViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)
}