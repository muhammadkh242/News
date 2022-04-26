package com.example.news.repository.model


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
