package com.example.newsapp20.model

data class News(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class NewsJsonResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)