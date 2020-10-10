package com.example.newsapp20.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val author: String? = "Author Unknown",
    val title: String? = "No Title",
    val description: String? = "Content description not available",
    val url: String? = "Cannot redirect",
    val urlToImage: String? = "Broken Image",
    val publishedAt: String? = "Date Not Available",
    val content: String? = "content is truncated"
): Parcelable

data class NewsJsonResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)