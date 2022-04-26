package com.example.news.repository.model

import com.google.gson.annotations.SerializedName


data class News(
    val status : String,
    val userTier : String,
    val total : Int,
    val startIndex : Int,
    val pageSize : Int,
    val currentPage : Int,
    val pages : Int,
    val orderBy : String,
    val results : List<Results>
)
data class Results(
    val id : String,
    val type : String,
    val sectionId : String,
    val sectionName : String,
    val webPublicationDate : String,
    val webTitle : String,
    val webUrl : String,
    val apiUrl : String,
    val isHosted : Boolean,
    val pillarId : String,
    val pillarName : String
)
data class APIResponse (

    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val articles : List<Articles>
)

data class Articles (

    @SerializedName("source") val source : Source,
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("urlToImage") val urlToImage : String,
    @SerializedName("publishedAt") val publishedAt : String,
    @SerializedName("content") val content : String
)

data class Source (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String
)