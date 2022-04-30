package com.example.news.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.ActivitySearchBinding
import com.example.news.databinding.NewsItemBinding
import com.example.news.repository.model.APIResponse
import com.example.news.repository.model.Articles
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter(private val context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var newsList: List<Articles> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = newsList[position]

        holder.binding.apply {
            title.text = currentItem.title
            name.text = currentItem.source.name
            if(currentItem.description !=null &&  currentItem.description.length > 85){
                description.text = "${currentItem.description.slice(IntRange(0,85))}...."
            }else{
                description.text = currentItem.description

            }
            author.text = currentItem.author
            date.text = currentItem.publishedAt.slice(IntRange(0,9))

            cardview.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.url))
                context.startActivity(browserIntent)
            }
            shareIcon.setOnClickListener {
                shareArticle(currentItem)
            }

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

    private fun shareArticle(article: Articles){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.setType("text/plain")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "${article.title} : ${article.url}")
        context.startActivity(Intent.createChooser(sharingIntent, "Share Article"))
    }

}