package com.example.newsapp20.detailScreen

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp20.model.News

class DetailScreenViewModel(
    news: News,
    application: Application
) : AndroidViewModel(application) {
    private val _selectedNewsItem = MutableLiveData<News>()

    val selectedNewsItem: LiveData<News>
        get() = _selectedNewsItem

    init {
        _selectedNewsItem.value = news
    }

    val displayPublishDate = Transformations.map(_selectedNewsItem){
        val formattedDate = it.publishedAt?.split("T")
        val date = formattedDate?.get(0)
        val time = formattedDate?.get(1)?.removeSuffix("Z")
    }

}